var express = require('express');
var router = express.Router();
var svgCaptcha = require('svg-captcha');

var httputil = require('../modules/http/httpUtil');
var constants = require('../modules/constant/constants');
var api = require('../modules/constant/api');
var random = require('../modules/util/randomUtil');


router.post('/findList_', function (req, res, next) {

    httputil.post(req, api.FINDFILELIST, req.body, function (data) {
        data.data.host=api.GEFILE;
        data.data.downHost=api.DOWNFILE;
        res.send(data);
    })
});

router.post('/delFile_', function (req, res, next) {
    httputil.post(req, api.DELFILE, req.body, function (data) {
        res.send(data);
    })
});


module.exports = router;