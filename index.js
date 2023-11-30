const express = require('express');
const routes = require('./routes');
const cors = require('cors');
const bodyParser = require('body-parser');
//const { loading } = require('./ml');

let model;
(async () => {
  // model = await loading();
  // console.log('Model Successfully Loaded...');

  const app = express();
  const port = 3000;

  app.use(cors());
  app.use(bodyParser.json());
  app.use(routes);

  app.listen(port, () => {
    console.log(`Server running on port http://localhost:${port}`);
  });
})();

module.exports = model;
