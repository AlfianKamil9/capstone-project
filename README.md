# Capstone Project Repository
 This repository was created for documentation and a collaborative platform in the Bangkit Capstone Project 2023 Bath 2, this is a back-end repository that uses Node.js and Google CLoud Platform.
## Dependencies Project (origin branch)
- Express Javascript Framework
- JsonWebToken
- Dotenv
- Bcrypt
- Mysql2
- Nodemon
- Sequelize
- Swagger-ui-express
- Validator
## Machine Learning Models
#### Model For Quiz
   
   ```ModelQuiz
   https://storage.googleapis.com/models-mechine-learning-ch2-ps134/model_model2/model.json
   ```
#### Model For Image

   ```ModelImage
   https://storage.googleapis.com/models-mechine-learning-cnn/cnn_1/model.json
   ```
#### Try the project
1. Clone Deployment Models
   
   ```Clone
   git clone -b deployment-ml-cc https://github.com/AlfianKamil9/capstone-project.git
   ```
2. Install dependencies
   
   ```Bash
   npm install
   ```
3. Run Script
   
   ```Script
   npm run dev
   ```
## Instalation 
If you want to try this project,
1. Clone this project
   
   ```Clone
   git clone -b main https://github.com/AlfianKamil9/capstone-project.git
   ````
2. Install dependencies

   ```Bash
   npm install
   ```
3. Add folder config/config.json

   ```Bashline
   {
    "development": {
      "username": "root",
      "password": null,
      "database": "Your-Db-Name",
      "host": "localhost",
      "dialect": "mysql"
      }
   }
   ```
4. Add file .env

   ```Bashenv
   # SECRET TOKEN
   TOKEN=Your-Token
   ```

5. Generate database
   
   ```Bashdb
   # Generate Table from Model & Migration
   npx sequelize-cli db:migrate
 
   # Generate data seeders
   npx sequelize-cli db:seed:all
 
   # Delete Table
   npx sequelize-cli db:migrate:undo
 
   # Delete Table
   npx sequelize-cli db:seed:undo:all
   ```

6. Run Project 

   ```Bashrun
   npm run start
   ```

## API Dokumentasi
Dokumentasi Sweager API
```Bashlink
http://localhost:3000/api-docs
```
Table of API
| Method | Routes | Token | Description |
| ---    |  ---   | ---  | ---         |
| `POST` | /api/v1/login | no | email: required;  password: required; |
| `POST` | /api/v1/register | no | email: required; name: required; familyEmail: required; password: required; confPassword: required,same; |
| `POST` | /api/v1/submit-quiz | required | a1: required; a2: required; a3: required; a4: required; a5: required; a6: required; a7: required; a8: required; a9: required; a10: required;|
| `GET`  | /api/v1/artikel | no | |
| `GET`  | /api/v1/artikel/{id} | no | |
| `GET` | /api/v1/questions | required | |
| `DELETE` | /api/v1/logout | required | |
## Deploy to APP Engine 
## Deploy to Vertex AI
