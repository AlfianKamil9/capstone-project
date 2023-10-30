const Users = require('../models/User');
const bcrypt = require('bcrypt');
const validator = require('validator');

const register = async (req, res) => {
  const { name, email, password, confPassword, familyEmail } = req.body;
  const cekEmail = await Users.findOne({ where: { email: email } });
  const salt = await bcrypt.genSalt();
  const hashPass = await bcrypt.hash(password, salt);

  if (!name || !email || !familyEmail || !password || !confPassword) return res.status(400).json({ code: 400, message: 'Error, Please All Fields are required to filled' });
  if (password !== confPassword) return res.status(400).json({ code: 400, message: 'Error, Please Password and Password Confirmation must be same' });
  if (!validator.isEmail(email) || !validator.isEmail(familyEmail)) return res.status(400).json({ code: 400, message: 'Error, Please Invalid Email Type' });
  if (cekEmail) return res.status(400).json({ code: 400, message: 'Error, Email was already used, Please use another email' });

  try {
    await Users.create({
      name: name,
      email: email,
      familyEmail: familyEmail,
      password: hashPass,
    });
    return res.status(201).json({
      code: 201,
      message: 'Success, Your account has been successfully created',
    });
  } catch (error) {
    return res.status(500).json({
      code: 500,
      message: 'Server Error',
    });
  }
};

const login = async (req, res) => {
  const { email, password } = req.body;

  if (!email || !password) return res.status(400).json({ code: 400, message: 'Error, Please All Fields are required to filled' });
  if (!validator.isEmail(email)) return res.status(400).json({ code: 400, message: 'Error, Please Invalid Email Type' });

  const cekUser = await Users.findOne({ where: { email: email } });
  if (!cekUser) return res.status(401).json({ code: 401, message: 'Error, Your Account is Not Found' });

  const passMatched = await bcrypt.compare(password, cekUser.password);
  if (!passMatched) return res.status(401).json({ code: 401, message: 'Error, Your Account is Not Found' });

  //return res.status(200).json({ code: 200, message: 'Successfull Login' });
};

const logout = async () => {};

module.exports = {
  register,
  login,
  logout,
};
