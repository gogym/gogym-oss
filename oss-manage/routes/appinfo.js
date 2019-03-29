var express = require('express');
var router = express.Router();
var httputil = require('../modules/http/httpUtil');

var api = require('../modules/constant/api');
var constants = require('../modules/constant/constants');

/**
 * 添加应用
 */
router.post('/addApp_', function (req, res, next) {

    req.body.devId = req.session.user.id;
    httputil.post(req, api.ADDAPP, req.body, function (data) {
        res.send(data);
    })
});

/**
 * 应用列表
 */
router.post('/applist_', function (req, res, next) {

    var params = {
        "devId": req.session.user.id,
        "start": isNaN(req.body.curr) ? null : parseInt(req.body.curr),
        "size": isNaN(req.body.limit) ? null : parseInt(req.body.limit)
    };
    httputil.post(req, api.APPLIST, params, function (data) {
        data.data.host = api.GEFILE;
        res.send(data);
    })
});


/**
 * 删除应用
 */
router.post('/delApp_', function (req, res, next) {

    var params = {"id": req.body.id};
    httputil.post(req, api.DELAPP, params, function (data) {
        res.send(data);
    })
});

/**
 * 更新应用
 */
router.post('/updateApp_', function (req, res, next) {

    httputil.post(req, api.UPDATEAPP, req.body, function (data) {
        res.send(data);
    })
});


/**
 * 更新密匙
 */
router.post('/updateAppSecret_', function (req, res, next) {

    httputil.post(req, api.UPDATESECRET, req.body, function (data) {
        res.send(data);
    })
});


/**
 * 查找应用
 */
router.post('/findApp_', function (req, res, next) {

    var params = {"id": req.body.id};
    httputil.post(req, api.FINDAPP, params, function (data) {
        data.data.host = api.GETIMG;
        res.send(data);
    })
});


module.exports = router;