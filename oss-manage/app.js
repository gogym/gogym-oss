var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var session = require('express-session');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var lessMiddleware = require('less-middleware');
var ejs = require('ejs');
const timeout = require('connect-timeout');
var app = express();

//如果集群部署，需要把session放到redis里，现单机不需要
var RedisStore = require('connect-redis')(session);
var options = {
    "host": "192.168.167.111",
    "port": "6379",
    "pass": "inhand@redis2017",
    "ttl": 60 * 30   //Session的有效期为30分钟
};

// view engine setup
app.set('views', path.join(__dirname, 'views'));
//使用html模板
app.engine('html', ejs.__express);
app.set('view engine', 'html');
app.use(logger('dev'));
app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({limit: '50mb', extended: false}));
app.use(cookieParser());
app.use(session({
    resave: false,
    rolling: true,
    saveUninitialized: true, // don't create session until something stored
    secret: 'love',
    cookie: {secure: false},
    //session放到redis中，这里先注释放到本地
    //store: new RedisStore(options)
}));
app.use(lessMiddleware(path.join(__dirname, 'public')));
app.use(express.static(path.join(__dirname, 'public')));
app.use(timeout('50s'));

//登录拦截器
app.use(function (req, res, next) {
    var url = req.originalUrl;
    var token = req.session.token;
    var simpleList = url.split("/");
    var simpleUrl = simpleList[1];
    var bool = false;

    //不需要拦截的url
    var paths = ["/login", "/authCode", "/register"];
    for (let index in paths) {
        var path = paths[index];
        if (url.indexOf(path) != -1) {
            bool = true;
            break;
        }
    }

    //session失效后跳转到登录页
    if (!bool && token == undefined) {
        if (req.headers['x-requested-with'] && req.headers['x-requested-with'].toLowerCase() == 'xmlhttprequest') {
            // AJAX请求
            res.send({"code": 10009, "msg": "请重新登录"});
        } else {
            // 普通请求
            res.send("<script language='javascript'>" + "parent.window.open('/login', '_top')" + "</script>");
        }
        return;
    }

    next();
});

//使用路由路径
app.use('/', require('./routes/index'));
app.use('/user', require('./routes/user'));
app.use('/appinfo', require('./routes/appinfo'));
app.use('/upload', require('./routes/common/upload_route'));
app.use('/home', require('./routes/home'));
app.use('/file', require('./routes/file'));


// catch 404 and forward to error handler
app.use(function (req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    res.send("<script language='javascript'>" + "window.open('/404', '_self')" + "</script>");
    next(err);
});

// error handler
app.use(function (err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};
    console.log(err.message);
    // render the error page
    res.status(err.status || 500);
    res.send("<script language='javascript'>" + "window.open('/500', '_self')" + "</script>");
});

module.exports = app;
