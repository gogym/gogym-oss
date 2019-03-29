var multer = require('multer');
var fs = require('fs');
var http = require('http');
var stream = require('stream');
//磁盘存储引擎
var storage = multer.diskStorage({
    //设置上传后文件路径，uploads文件夹会自动创建。
    destination: function (req, file, cb) {
        cb(null, 'upload/')
    },
    //给上传文件重命名，获取添加后缀名
    filename: function (req, file, cb) {
        cb(null, file.originalname);
    }
});

//内存存储引擎将文件存储在内存中的 Buffer 对象. 它没有任何选项
var memoryStorage = multer.memoryStorage();

//设置储存位置
var upload = multer({
    storage: memoryStorage
});


//上传方法
function uploadFile(file, token, options, callback) {
    // 判断文件是否存在
    fs.exists(file.path, function (exists) {
        if (!exists) {
            callback("没有找到目标文件，上传失败");
            return;
        }
        console.log("找到文件准备上传");
    });

    var fileKey = "filedata";//与服务器对应，一般是固定值
    var fileName = file.originalname;

    var CHARSET = 'UTF-8';
    var BOUNDARY = Date.now(); //时间戳，目的是防止上传文件中出现分隔符导致服务器无法正确识别文件起始位置
    var PREFIX = "--", LINE_END = "\r\n";
    var CONTENT_TYPE = "multipart/form-data"; // 内容类型

    //
    var payload = PREFIX + BOUNDARY + LINE_END + "Content-Disposition: form-data; name=\"" + fileKey
        + "\"; filename=\"" + fileName + "\"" + LINE_END + "Content-Type: multipart/form-data; charset="
        + CHARSET + LINE_END + LINE_END;

    var end_data = LINE_END + PREFIX + BOUNDARY + PREFIX;

    var reqHttps = http.request(options, function (resHttps) {

        var data = '';
        //请求成功回调
        resHttps.on('data', function (body) {
            data += body;
        });
        //请求完成
        resHttps.on('end', function () {
            callback(data);
        });
    });

    //设置请求头
    reqHttps.setHeader('Content-Type', 'multipart/form-data; boundary=' + BOUNDARY + '');
    reqHttps.setHeader('Content-Length', Buffer.byteLength(payload) + Buffer.byteLength(end_data) + file.size);
    if (token != null) {
        reqHttps.setHeader('Token', token);
    }
    //先把负载类型写入
    reqHttps.write(payload);
    //把文件通过文件流发送
    var fileStream = fs.createReadStream(file.path, {bufferSize: file.size});
    fileStream.pipe(reqHttps, {end: false});
    //写入结束符号
    fileStream.on('end', function () {
        reqHttps.end(end_data);
        // 上传完成后删除文件
        fs.unlink(file.path, function () {
            console.log('success');
        });
    });

    //请求错误回调
    reqHttps.on('error', function (e) {
        // 上传错误删除文件
        fs.unlink(file.path, function () {
            console.log('success');
        });
        console.error("error:" + e);
    });

}

//上传方法
function uploadFileBuffer(file, token, options, callback) {

    var fileKey = "filedata";//与服务器对应，一般是固定值
    var fileName = file.originalname;

    var CHARSET = 'UTF-8';
    var BOUNDARY = Date.now(); //时间戳，目的是防止上传文件中出现分隔符导致服务器无法正确识别文件起始位置
    var PREFIX = "--", LINE_END = "\r\n";
    var CONTENT_TYPE = "multipart/form-data"; // 内容类型

    //
    var payload = PREFIX + BOUNDARY + LINE_END + "Content-Disposition: form-data; name=\"" + fileKey
        + "\"; filename=\"" + fileName + "\"" + LINE_END + "Content-Type: multipart/form-data; charset="
        + CHARSET + LINE_END + LINE_END;

    var end_data = LINE_END + PREFIX + BOUNDARY + PREFIX;

    var reqHttps = http.request(options, function (resHttps) {

        var data = '';
        //请求成功回调
        resHttps.on('data', function (body) {
            data += body;
        });
        //请求完成
        resHttps.on('end', function () {
            callback(data);
        });
    });

    //设置请求头
    reqHttps.setHeader('Content-Type', 'multipart/form-data; boundary=' + BOUNDARY + '');
    reqHttps.setHeader('Content-Length', Buffer.byteLength(payload) + Buffer.byteLength(end_data) + file.size);
    if (token != null) {
        reqHttps.setHeader('Token', token);
    }
    //先把负载类型写入
    reqHttps.write(payload);

    // 创建一个bufferstream
    var fileStream = new stream.PassThrough();
    //将Buffer写入
    fileStream.end(file.buffer);
    fileStream.pipe(reqHttps, {end: false});
    //写入结束符号
    fileStream.on('end', function () {
        reqHttps.end(end_data);
    });

    //请求错误回调
    reqHttps.on('error', function (e) {
        console.error("error:" + e);
    });

}

module.exports.upload = upload;
module.exports.uploadFile = uploadFile;
module.exports.uploadFileBuffer = uploadFileBuffer;