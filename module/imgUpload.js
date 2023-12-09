'use strict';
const { Storage } = require('@google-cloud/storage');
//const fs = require('fs');
const process = require('process');
const path = require('path');
const pathKey = path.resolve('./serviceaccountkey.json');

const storage = new Storage({
  projectId: process.env.PROJECT_ID, // Ganti dengan ID proyek Google Cloud Anda
  keyFilename: pathKey, // Ganti dengan path ke file kredensial JSON
});
const bucketName = process.env.BUCKET_NAME; // ganti nama bucket
const bucket = storage.bucket(bucketName);

function getPublicUrl(filename) {
  return 'https://storage.googleapis.com/' + bucketName + '/' + filename;
}

let ImgUpload = {};
ImgUpload.uploadToGcs = (req, res, next) => {
  if (!req.file) return next();

  const gcsname = Date.now();
  const file = bucket.file(gcsname);

  const stream = file.createWriteStream({
    metadata: {
      contentType: req.file.mimetype,
    },
  });

  stream.on('error', (err) => {
    req.file.cloudStorageError = err;
    next(err);
  });

  stream.on('finish', () => {
    req.file.cloudStorageObject = gcsname;
    req.file.cloudStoragePublicUrl = getPublicUrl(gcsname);
    next();
  });

  stream.end(req.file.buffer);
};

module.exports = ImgUpload;
