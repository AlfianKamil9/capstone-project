const { Sequelize } = require('sequelize');

const db = new Sequelize('capstone_project', 'root', '', {
  host: 'localhost',
  dialect: 'mysql',
});

module.exports = db;
