var express = require('express');
var router = express.Router();
var svgCaptcha = require('svg-captcha');

var httputil = require('../modules/http/httpUtil');
var constants = require('../modules/constant/constants');
var api = require('../modules/constant/api');
var random = require('../modules/util/randomUtil');


/**
 * 跳到注册页
 */
router.get('/register', function (req, res, next) {
    res.render('user/reg.html');
});

/**
 * 跳转到登录页面
 */
router.get('/login', function (req, res, next) {
    res.render('user/login.html');
});


/**
 * 退出登录
 */
router.get('/logout', function (req, res, next) {
    req.session.token = null;
    req.session.user = null;

    res.send({"code": 10000, "msg": "成功"});
});

/**
 * 获取验证码
 */
router.get('/authCode', function (req, res, next) {
    var codeConfig = {
        size: 4,// 验证码长度
        ignoreChars: '0o1i', // 验证码字符中排除 0o1i
        noise: 3, // 干扰线条的数量
        color: true,////字符将具有不同的颜色而不是灰色，如果设置了background选项，则为true
        background: '#cc9966',// svg图像的背景颜色
        width: 140,
        height: 35,
        fontSize: 35
    }
    var captcha = svgCaptcha.create(codeConfig);
    req.session.captcha = captcha.text.toLowerCase(); //存session用于验证接口获取文字码
    res.type('svg');
    res.send(captcha.data);
});


/**
 * 跳转到首页
 */
router.get('/', function (req, res, next) {
    //获取用户昵称
    var nickname = req.session.user.nickname;
    res.render('index.html', {"nickname": nickname});
});


/**
 * 跳转到控制台
 */
router.get('/console', function (req, res, next) {

    var params={"devId":req.session.user.id};

    httputil.post(req, api.COUNTDATA, params, function (data) {
        console.log(data);
        res.render('home/homepage.html', {"data": data.data});
    })


});

/**
 * 跳转到应用列表
 */
router.get('/applist', function (req, res, next) {
    res.render('applist.html');
});

/**
 * 跳转到文件列表
 */
router.get('/filelist', function (req, res, next) {
    res.render('file/filelist.html');
});

/**
 * 跳转到添加应用
 */
router.get('/addApp', function (req, res, next) {
    res.render('app/addapp.html');
});


/**
 * 跳转到404
 */
router.get('/404', function (req, res, next) {
    res.render('home/404.html');
});

/**
 * 跳转到500
 */
router.get('/500', function (req, res, next) {
    res.render('home/error.html');
});


/**
 * 跳转到应用编辑
 */
router.get('/editApp', function (req, res, next) {
    res.render('app/addapp.html');
});

/**
 * 跳转个人详情
 */
router.get('/userInfo', function (req, res, next) {
    res.render('user/info.html', {"user": req.session.user});
});

/**
 * 跳转修改密码
 */
router.get('/setPass', function (req, res, next) {
    res.render('user/password.html', {"user": req.session.user});
});


module.exports = router;