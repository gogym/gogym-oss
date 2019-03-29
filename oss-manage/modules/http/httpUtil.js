//使用request更加简便
var request = require('request');
//加解密工具类
var cryptoUtil = require('../util/cryptoUtil');
var randomUtil = require('../util/randomUtil');


//get请求
function get(req, url, params, callback) {

    if (params == null) {
        params = {};
    }
    params["timestamp"] = new Date().getTime();
    params["nonce"] = randomUtil.generateString(6);

    //转成json字符串
    var jsonStr = JSON.stringify(params);
    console.log("params:", jsonStr);
    url += "?data=" + encodeURIComponent(jsonStr);

    request({
        url: url,
        method: "GET",
        json: true,
        headers: {
            "Content-type": "application/x-www-form-urlencoded;charset=UTF-8",
            "Token": req.session.token
        },
        timeout: 20000
    }, function (error, response, body) {
        if (!error && response.statusCode == 200) {
            callback(body);
        } else {
            console.log("请求失败：" + error);
        }
    });


}

//post请求
function post(req, url, params, callback) {
    if (params == null) {
        params = {};
    }
    //添加时间戳和随机数
    params["timestamp"] = new Date().getTime();
    params["nonce"] = randomUtil.generateString(10);

    console.log("params:", params);

    request({
        url: url,
        method: "POST",
        json: true,
        headers: {
            "Content-type": "application/x-www-form-urlencoded;charset=UTF-8",
            "Token": req.session.token
        },
        timeout: 20000,
        //body: JSON.stringify(params)
        form: params
    }, function (error, response, body) {
        if (!error && response.statusCode == 200) {
            callback(body);
        } else {
            console.log("请求失败：" + error);
        }
    });
}


//修正未定义的变量
function checkUnfined(value) {
    if (typeof(value) == 'undefined') {
        return "";
    }
    return value;
}

module.exports.get = get;
module.exports.post = post;
module.exports.checkUnfined = checkUnfined;

