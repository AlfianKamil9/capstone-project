const { Answer } = require('../models');
//const { Storage } = require('@google-cloud/storage');
const axios = require('axios');
const process = require('process');

// INISIASI CLOUD STORAGE

// CONTROLLER SUBMIT QUIZ
const submitQuiz = async (req, res) => {
  const userId = req.userData.id;
  const { a1, a2, a3, a4, a5, a6, a7, a8, a9, a10 } = req.body;
  const allVariablesNotNull = a1 !== null && a2 !== null && a3 !== null && a4 !== null && a5 !== null && a6 !== null && a7 !== null && a8 !== null && a9 !== null && a10 !== null;
  //cek variabel terisi atau tidak
  if (!allVariablesNotNull) {
    return res.status(400).json({
      code: 400,
      message: 'Semua data harus diisi.',
    });
  }
  const input = [a1, a2, a3, a4, a5, a6, a7, a8, a9, a10].map((e) => {
    switch (e.toLowerCase()) {
      case 'a':
        return 0;
      case 'b':
        return 1;
      case 'c':
        return 2;
      default:
        return 3;
    }
  });
  const jsonString = JSON.stringify(input);
  console.log(jsonString);
  try {
    // const url = process.env.ML_URL + '/predict-form';
    // const request = await axios.post(url, input1, {
    //   headers: {
    //     Authorization: `Baerer ${process.env.ML_KEY}`,
    //     'Content-Type': 'application/json',
    //   },
    // });
    // const result = request.data;
    // console.log(result);

    const sendAnswer = await Answer.create({
      userId: userId,
      answerForm: jsonString,
      answerImage: 0,
      createdAt: new Date(),
      updatedAt: new Date(),
    });

    // cek response
    if (sendAnswer) {
      return res.status(200).json({
        code: 200,
        message: 'Berhasil Maukin List Jawaban',
      });
    }
    return res.status(500).json({
      code: 500,
      message: 'Server Error',
    });
  } catch (error) {
    console.error(error);
    return res.status(500).json({
      code: 500,
      message: 'Server Error',
    });
  }
};

// SUBMIT IMAGE
const submitImage = async (req, res) => {
  const data = req.body;
  if (req.file && req.file.cloudStoragePublicUrl) {
    data.imageUrl = req.file.cloudStoragePublicUrl;
  }

  // mendapatkan answerForm untuk diproses
  const userId = req.userData.id;
  const allData = await Answer.findAll({
    where: {
      userId: userId,
    },
    order: [['id', 'DESC']],
  });

  // DEFINISI NILAI ANSWER FORM
  const answerForm = JSON.parse(allData[0].answerForm);
  console.log('nilai answerForm dari db:', answerForm);

  // DEFINISI IMAGE
  const input = data.imageUrl;

  // DEFINISI KIRIMAN ANSWER DAN IMAGE
  const inputBahan = { input, answerForm };
  console.log(inputBahan);

  // MENDEFINISIKAN TRY CATCH
  try {
    const url = process.env.ML_URL + '/predict';
    const request = await axios.post(url, inputBahan, {
      headers: {
        Authorization: `Baerer ${process.env.ML_KEY}`,
        'Content-Type': 'application/json',
      },
    });
    const result = request.data;
    await Answer.update(
      { answerImage: input }, // Data yang akan diupdate
      { where: { id: allData[0].id } }          // Kondisi untuk memilih data yang akan diupdate
    );
    if (result.code == 200) {
      res.status(200).json({
        code: 200,
        message: result.message,
        data: result.data,
      });
    }
    return res.status(500).json({
      code: 500,
      message: result.message,
    });
  } catch (error) {
    console.error(error);
    return res.status(500).json({
      code: 500,
      message: 'Internal Server Error',
    });
  }
};

module.exports = {
  submitQuiz,
  submitImage,
};
