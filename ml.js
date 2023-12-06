const { argMax } = require('@tensorflow/tfjs-node');
const tfjs = require('@tensorflow/tfjs-node');

let model;

async function loadModel() {
  try {
    const modelUrl = `https://storage.googleapis.com/models-mechine-learning-ch2-ps134/model_model2/model.json`;
    console.log('Please Waiting... Model for Quiz is Loaded');
    const model = await tfjs.loadLayersModel(modelUrl);
    return model;
  } catch (error) {
    console.log(error);
  }
}

async function loadModelImage() {
  try {
    const model = `https://storage.googleapis.com/models-mechine-learning-cnn/cnn_1/model.json`;
    console.log('Please Waiting... Model for Image is Loaded');
    const model2 = await tfjs.loadLayersModel(model);
    return model2;
  } catch (error) {
    console.log(error);
  }
}
function predict(model, input) {
  try {
    const inputed = tfjs.tensor([input]);
    console.log(inputed);
    const arg = model.predict(inputed);
    const argIndex = arg.argMax(1).dataSync();
    return argIndex;
  } catch (error) {
    console.log(error);
  }
}

function predictImage(model, input) {
  try {
    const tensor = tfjs.node.decodeJpeg(input).resizeNearestNeighbor([200, 200]).expandDims().toFloat();
    console.log(tensor);
    return model.predict(tensor).data();
  } catch (error) {
    console.log(error);
  }
}

module.exports = {
  loadModel,
  loadModelImage,
  predict,
  predictImage,
};
