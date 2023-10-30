const express = require('express');
const routes = require('./routes');
const DB = require('./config/koneksi');
const app = express();
const port = 3000;

app.use(routes);
try {
  DB.authenticate();
  console.log('Database connected successfully');
} catch (error) {
  console.error('Error connecting to database:', error);
}

app.listen(port, () => {
  console.log(`Server running on port http://localhost:${port}`);
});
