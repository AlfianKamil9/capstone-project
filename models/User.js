const { Sequelize } = require('sequelize');
const db = require('../config/koneksi');

const { DataTypes } = Sequelize;

const Users = db.define('users', {
  name: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  email: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  familyEmail: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  password: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  token: {
    type: DataTypes.TEXT,
  },
});

//Users.sync();
module.exports = Users;
