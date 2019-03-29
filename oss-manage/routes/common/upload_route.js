var express = require('express');
var router = express.Router();
var fs = require('fs');
var uploadUtil = require('../../modules/util/uploadUtil');
var api = require('../../modules/constant/api');

var uploadHost = 'localhost';//文件服务器地址

//上传文件方法
router.post('/file-upload', uploadUtil.upload.single('file'), function (req, res) {
    var options = {
        host: uploadHost,//文件服务器地址
        port: 1703,
        path: '/file/uploadFile',
        method: 'post'
    };
    uploadUtil.uploadFileBuffer(req.file, null, options, function (data) {
        console.log(data);
        var json = JSON.parse(data);
        var path = json.data.path;
        json.data.pathfull = "http://" + uploadHost + ":1703/file/getFile/" + path;
        res.send(json);
    });

});


module.exports = router;