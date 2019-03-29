var express = require('express');
var router = express.Router();
var httputil = require('../modules/http/httpUtil');

var api = require('../modules/constant/api');
var constants = require('../modules/constant/constants');


/**
 * 获取用量统计
 */
router.post('/getStatistics_', function (req, res, next) {

    req.body.devId = req.session.user.id;
    httputil.post(req, api.GETSTATISTICS, req.body, function (data) {
        var date = new Array();
        var len = new Array();
        var num = new Array();

        data.data.forEach(function (v) {
            date.push(v.date);
            len.push(v.len);
            num.push(v.num);
        });

        var tmp = {"date": date, "len": len, "num": num};
        data.data = tmp;
        res.send(data);
    })
});


router.post('/statisticsApi_', function (req, res, next) {
    req.body.devId = req.session.user.id;
    httputil.post(req, api.STATISTICSAPI, req.body, function (data) {
        var date = new Array();
        var read = new Array();
        var write = new Array();

        data.data.list.forEach(function (v) {
            date.push(v.clickDate);
            read.push(v.read);
            write.push(v.write);
        });

        var tmp = {"date": date, "read": read, "write": write, "countExtension": data.data.countExtension};
        data.data = tmp;
        res.send(data);
    })
});


module.exports = router;