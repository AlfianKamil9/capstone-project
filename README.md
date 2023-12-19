# Machine Learning
# BCare - Bangkit Capstone Project 2023 - [CH2-PS134]

<div style="display: flex; gap: 50px; margin-top: 20px; margin-bottom: 20px">
  <img src="/tensorflow.png" alt="image" width="300" height="300">
</div>

This repository was created to explain the **Machine Learning** part of the Bcare project, where the process was created using the Python language and primarily using the Tensorflow package.

## Dataset
- [Form Dataset](https://drive.google.com/file/d/13ew-_lRZ3bO9-qMh108qaXCnn8V8K0Zf/view?usp=sharing)
- [Image Dataset](https://github.com/AlfianKamil9/capstone-project/tree/ml/dataset_babyblues2/dataset_babyblues
)

## Data Processing
- Packages used in data processing :
1. Numpy
2. Pandas
3. Scikit-learn
4. Tensorflow
5. Matplotlib
- Steps taken in pre-process data:
1. Data Cleaning:
   - Missing Values: Handling empty or NaN values through imputation or deletion.
   - Outliers: Identifying and dealing with data points far from the general pattern.
2. Feature Encoding/Transformation
   - Categorical Variables: Transforming categorical variables into numerical form using One-Hot Encoding or Label Encoding.
3. Splitting Data
   - Separating data into training and testing sets to evaluate the model's performance.

## Modeling
### Modeling Form
For the model form, we used a regular neural network with a structure like this,

<img src="/124.png" alt="gambarstrukturmodelform" width="600" height="600">

which resulted in a final accuracy of 99.1%.

[Link Model Form untuk Deployment](https://drive.google.com/drive/folders/1-RzDhIA9HAsLO06mPPswGuCgXRlvVa9j?usp=sharing)

### Modeling Image
For image modeling, we use a Convolutional Neural Network inspired by the ResNet50 structure with a structure like this, 

<img src="/123.png" alt="gambarstrukturmodelimage" width="600" height="600">

which produces a final accuracy of 97.2%.

[Link Model Image untuk Deployment](https://drive.google.com/drive/folders/1-0HUsxUBi5aFi8nFtRr7BiGgKYS4Q_nM?usp=sharing)
