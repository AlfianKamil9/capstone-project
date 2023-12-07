// index.js
const express = require('express');
const multer = require('multer');
const bodyParser = require('body-parser');
const { loadModel, predict, loadModelImage, predictImage } = require('./ml'); // Sesuaikan dengan path yang benar
(async () => {
  const kondisi = ['Aman', 'Depresi Ringan', 'Kemungkinan Depresi', 'Depresi Berat'];
  const model = await loadModel();
  console.log('Model for Quiz Successfully loaded');
  const modelImage = await loadModelImage();
  console.log('Model for Image Successfully loaded');

  const app = express();
  const port = 5000;
  // Middleware untuk mengolah JSON
  const upload = multer();
  app.use(bodyParser.json());

  //TOKEN
  const keyToken = 'capstone-project-machine-learning';

  // set Token
  const token = (req, res, next) => {
    const { authorization } = req.headers;
    const token = authorization.split(' ')[1];
    if (!authorization || token != keyToken) {
      return res.status(401).json({ error: 'Unauthorized: Invalid token' });
    }
    next();
  };

  app.use(token);

  app.post('/predict-form', async (req, res) => {
    const { input } = req.body;

    // Lakukan prediksi menggunakan fungsi predict
    const result = await predict(model, input);
    console.log(result);
    console.log(result[0]);
    return res
      .json({
        code: 200,
        message: 'Complete predict form',
        data: kondisi[result[0]],
      })
      .status(201);
  });

  app.post('/predict-image', upload.single('file'), async (req, res) => {
    const image = req.file.buffer;
    console.log(image);
    const result = await predictImage(modelImage, image);
    console.log(result);
    console.log(result[0]);
    return res
      .json({
        code: 200,
        message: 'Complete predict image',
        data: kondisi[result[0]],
      })
      .status(201);
  });

  // Jalankan server pada port tertentu
  app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
  });
})();
