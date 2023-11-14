const express = require('express');
const router = express.Router();
const artikelController = require('./controller/artikelController');
const authController = require('./controller/authController');
const questionController = require('./controller/questionController');
const jwt = require('jsonwebtoken');
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
router.get('/artikel', artikelController.getArtikels);
router.get('/artikel/:id', artikelController.getDetailArtikel);
router.post('/login', authController.login);
router.post('/register', authController.register);
// AUTH
router.get('/questions', authRules, questionController);
router.delete('/logout', authRules, authController.logout);

module.exports = router;
