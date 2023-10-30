const { Sequelize } = require('sequelize');
const db = require('../config/koneksi');

const { DataTypes } = Sequelize;

const Artikels = db.define('artikels', {
  title: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  image: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  subTitle: {
    type: DataTypes.STRING,
  },
  content: {
    type: DataTypes.TEXT,
    allowNull: false,
  },
});

//Artikels.sync();
module.exports = Artikels;
