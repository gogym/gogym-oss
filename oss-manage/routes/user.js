var express = require('express');
var router = express.Router();

var httputil = require('../modules/http/httpUtil');
var constants = require('../modules/constant/constants');
var api = require('../modules/constant/api');
var ResponseData = require("../modules/common/responseData")


/**
 * 登录
 */
router.post('/login_', function (req, res, next) {

    //获取验证码
    var vercode = req.body.vercode;
    // if (vercode != req.session.captcha) {
    //     var response = new ResponseData(constants.ERROR, "验证码错误");
    //     res.send(response);
    //     return;
    // }
    httputil.post(req, api.LOGIN, req.body, function (data) {
        if (data["code"] == 10000) {

            if (data.data.user.imgUrl) {
                data.data.user.fullImgUrl = api.GEFILE + data.data.user.imgUrl;
            }
            req.session.token = data["data"]["token"];
            req.session.user = data["data"]["user"];

            //把data清除，不返回到页面
            data["data"] = {};
        }
        res.send(data);
    })

});

/**
 * 登录
 */
router.post('/register_', function (req, res, next) {

    httputil.post(req, api.REGISTER, req.body, function (data) {
        res.send(data);
    })

});


/**
 * 更新
 */
router.post('/updateOssDeveloper_', function (req, res, next) {

    req.body.id = req.session.user.id;
    httputil.post(req, api.UPDATAOSSUSER, req.body, function (data) {
        res.send(data);
    })

});


/**
 * 更新
 */
router.post('/updatePass_', function (req, res, next) {

    if (req.body.oldPassword != req.session.user.password) {
        var response = new ResponseData("10001", "旧密码错误");
        res.send(response);
        return;
    }

    req.body.id = req.session.user.id;
    httputil.post(req, api.UPDATAOSSUSER, req.body, function (data) {

        if (data["code"] == 10000) {
            req.session.user.password = req.body.password;
        }

        res.send(data);
    })

});


module.exports = router;