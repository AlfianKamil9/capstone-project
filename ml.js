const tfjs = require('@tensorflow/tfjs-node');

function loading() {
  loadModel();
  console.log('Waiting Model Loaded...');
}

function loadModel() {
  const modelUrl = `https://storage.googleapis.com/models-mechine-learning-ch2-ps134/model_model2/model.json`;
  const model = tfjs.loadLayersModel(modelUrl);
  console.log('Prepare Model...');
  return model;
}

function predict(model, input) {
  const kondisi = ['Aman', 'Depresi Ringan', 'Kemungkinan Depresi', 'Depresi Berat'];
  const inputed = tfjs.tensor([input]);
  console.log(inputed);
  const arg = model.predict(inputed);
  const argIndex = arg.argMax(1).dataSync();
  return kondisi[argIndex[0]];
}

module.exports = { loadModel, predict, loading };
