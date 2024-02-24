const { argMax } = require('@tensorflow/tfjs-node');
const tfjs = require('@tensorflow/tfjs-node');
const kondisi = ['Aman', 'Depresi Ringan', 'Kemungkinan Depresi', 'Depresi Berat'];
let model;

async function loadModel() {
  try {
    const modelUrl = `https://storage.googleapis.com/storage-tugas-akhir-gcloud/model-machine-learning/form-model/model.json`;
    console.log('Please Waiting... Model for Quiz is Loaded');
    const model = await tfjs.loadLayersModel(modelUrl);
    return model;
  } catch (error) {
    console.log(error);
  }
}

async function loadModelImage() {
  try {
    const model = `https://storage.googleapis.com/storage-tugas-akhir-gcloud/model-machine-learning/image-model/model.json`;
    console.log('Please Waiting... Model for Image is Loaded');
    const model2 = await tfjs.loadLayersModel(model);
    return model2;
  } catch (error) {
    console.log(error);
  }
}
function predict(modelImage, modelForm, inputImage, inputForm) {
  try {
    // INPUT FORM
    const InForm = tfjs.tensor([inputForm]);

    // INPUT IMAGE
    const tensor = tfjs.node.decodeImage(inputImage).resizeNearestNeighbor([200, 200]).mean(2).expandDims(2);
    const tensor2 = tensor.toFloat().div(tfjs.scalar(255));
    const InImage = tensor2.expandDims(0);

    // PREDICT
    const predictForm = modelForm.predict(InForm);
    const predictImage = modelImage.predict(InImage);
    console.log('PredictForm :', predictForm, 'PredictImage :', predictImage);

    // PREDICT NO COMBINE
    //FORM
    console.log('PREDICT FORM :', predictForm.argMax(1).dataSync()[0]);
    //IMAGE
    console.log('PREDICT IMAGE :', predictImage.argMax(1).dataSync()[0]);

    // COMBINE PREDICT
    const combine = tfjs.concat([predictForm, predictImage], 1);
    console.log('Hasil Combine :', combine);

    // AVARAGE
    const averageProbabilitas = combine.mean(0).dataSync();
    console.log('Average Probabilitas :', averageProbabilitas);

    // CHOOSE PROBABILITAS
    const probabilitas = tfjs.argMax(averageProbabilitas).dataSync()[0];
    console.log('Probabilitas :', probabilitas);
    const finalResult = kondisi[probabilitas];

    // console.log(arg);
    // const argIndex = arg.argMax(1).dataSync();
    return finalResult;
  } catch (error) {
    console.log(error);
  }
}

module.exports = {
  loadModel,
  loadModelImage,
  predict,
};