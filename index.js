// index.js
const express = require('express');
const multer = require('multer');
const fetch = require('node-fetch');
const bodyParser = require('body-parser');
const { loadModel, predict, loadModelImage, predictImage } = require('./ml'); // Sesuaikan dengan path yang benar
(async () => {
  const modelForm = await loadModel();
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

  app.post('/predict', async (req, res) => {
    // tangkap request body
    const { input, answerForm } = req.body;
    const image = await fetch(input);

    // ambil gambar dari url
    const inputImage = await image.buffer();
    const inputForm = answerForm;
    console.log('Input Image :', inputImage, ' , ', 'Input Form :', inputForm);

    // Predict Image
    const result = await predict(modelImage, modelForm, inputImage, inputForm);
    console.log(result);

    if (result) {
      return res
        .json({
          code: 200,
          message: 'Complete predict image',
          data: result,
        })
        .status(200);
    }

    res.status(500).json({
      code: 500,
      message: 'Error predict',
    });
  });

  // Jalankan server pada port tertentu
  app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
  });
})();
