//const { Answer } = require('../models');
const { predict, loadModel } = require('../ml');

const submitQuiz = async (req, res) => {
  const model = await loadModel();
  console.log('model Loaded');
  const { a1, a2, a3, a4, a5, a6, a7, a8, a9, a10 } = req.body;
  const allVariablesNotNull = a1 !== null && a2 !== null && a3 !== null && a4 !== null && a5 !== null && a6 !== null && a7 !== null && a8 !== null && a9 !== null && a10 !== null;
  //cek variabel terisi atau tidak
  if (!allVariablesNotNull) {
    return res.status(400).json({
      code: 400,
      message: 'Semua data harus diisi.',
    });
  }
  // array
  const arrayMap = [a1, a2, a3, a4, a5, a6, a7, a8, a9, a10].map((e) => {
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
  console.log('Array yang mau dikirim :', arrayMap);
  const result = await predict(model, arrayMap);
  // jika kosong
  if (!result) {
    return res.json({
      code: 500,
      message: 'error',
    });
  }
  // get prediction result
  console.log('Prediksi : ', result);
  return res.json({
    code: 200,
    message: result,
  });
};

//
// const submitImage = async (req, res) => {
//   const { id } = req.params;
//   const { image } = req.body;

//   // cek image
//   if (!image) {
//     return res.status(400).json({
//       code: 400,
//       message: 'Data image harus diisi.',
//     });
//   }
//   try {
//     Answer.update(
//       {
//         image: image,
//       },
//       {
//         returning: true,
//         where: { userId: req.userData.id, id: id },
//       }
//     );
//     return res.status(200).json({
//       code: 200,
//       message: 'Submit Image Ok',
//     });
//   } catch (error) {
//     return res.status(500).json({
//       code: 500,
//       message: error.message,
//     });
//   }
// };

module.exports = {
  submitQuiz,
  // submitImage,
};
