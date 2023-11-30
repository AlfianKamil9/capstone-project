const { Artikel } = require('../models');
// GET ARTIKEL
const getArtikels = async (req, res) => {
  try {
    const data = await Artikel.findAll();
    return res.status(200).json({
      code: 200,
      message: 'Success Get Data Artikel',
      data: data,
    });
  } catch (error) {
    return res.status(500).json({
      code: 500,
      message: error,
    });
  }
};

// GET DETAIL ARTIKEL
const getDetailArtikel = async (req, res) => {
  try {
    const { id } = req.params;
    const dataId = await Artikel.findOne({ where: { id: id } });
    if (dataId) {
      return res.status(200).json({
        code: 200,
        message: 'Detail Artikel',
        data: dataId,
      });
    }
    return res.status(404).json({
      code: 404,
      message: 'Not Found',
      data: null,
    });
  } catch (error) {
    return res.status(500).json({
      code: 500,
      message: error,
    });
  }
};

module.exports = {
  getArtikels,
  getDetailArtikel,
};
