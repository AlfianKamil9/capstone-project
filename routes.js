const express = require('express');
const router = express.Router();
const artikelController = require('./controller/artikelController');
const authController = require('./controller/authController');
router.use(express.json()); // for parsing application/json
router.use(express.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded

router.get('/', (req, res) => {
  res.send('Hello World!');
});
router.get('/artikels', artikelController.getArtikels);
router.get('/artikel/:id', artikelController.getDetailArtikel);
router.post('/login', authController.login);
router.post('/register', authController.register);
// AUTH
router.get('/logout', authController.logout);

module.exports = router;
