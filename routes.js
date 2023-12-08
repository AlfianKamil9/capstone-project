const express = require('express');
const Multer = require('multer');
const router = express.Router();
const artikelController = require('./controller/artikelController');
const authController = require('./controller/authController');
const questionController = require('./controller/questionController');
const submitToMLController = require('./controller/submitToMLController');
const jwt = require('jsonwebtoken');
const ImgUpload = require('./module/imgUpload');

// kONFIGURASI MULTER
const upload = Multer({
  storage: Multer.memoryStorage(),
  limits: {
    fileSize: 5 * 1024 * 1024, // Batas ukuran file (5 MB dalam contoh ini)
  },
});

const sweagerUI = require('swagger-ui-express');
const apiDocs = require('./apiDocumentation.json');
router.use(express.json()); // for parsing application/json
router.use(express.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded
const tokenBlacklist = new Set();

// Middleware Login
const authRules = (req, res, next) => {
  const { authorization } = req.headers;

  if (!authorization)
    return res.status(401).json({
      code: 401,
      message: 'Token is required',
    });
  const token = authorization.split(' ')[1];
  try {
    if (tokenBlacklist.has(token)) {
      return res.status(401).json({ code: 401, message: 'Token has been invalidated' });
    }
    const jwtDecode = jwt.verify(token, process.env.TOKEN);
    req.userData = jwtDecode;
  } catch (error) {
    return res.status(401).json({ code: 401, message: 'Unathorized' });
  }
  next();
};

// SWAGGER ROUTES DOCS
router.use('/api-docs', sweagerUI.serve, sweagerUI.setup(apiDocs));
// ROUTES
router.get('/', (req, res) => {
  res.send('Hello World!');
});
router.get('/api/v1/artikel', artikelController.getArtikels);
router.get('/api/v1/artikel/:id', artikelController.getDetailArtikel);
router.post('/api/v1/login', authController.login);
router.post('/api/v1/register', authController.register);
// AUTH
router.get('/api/v1/questions', authRules, questionController);
router.post('/api/v1/submit-quiz', authRules, submitToMLController.submitQuiz);
router.post('/api/v1/submit-image', authRules, upload.single('file'), ImgUpload.uploadToGcs, submitToMLController.submitImage);
router.get('/api/v1/user', authRules, authController.users);
router.delete('/api/v1/logout', authRules, authController.logout);

module.exports = router;
