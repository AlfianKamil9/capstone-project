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
## Mechine Learning Models
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

## Deploy to Compute Engine Instance
## Deploy to APP Engine 
## Deploy to Vertex AI
