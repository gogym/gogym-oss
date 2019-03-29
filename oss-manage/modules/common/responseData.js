module.exports = ResponseData;

ResponseData.prototype.code;
ResponseData.prototype.msg;
ResponseData.prototype.data;

function ResponseData() {
    //构造方法内的属性和方法
}

function ResponseData(code, msg) {
    //构造方法内的属性和方法
    this.code = code;
    this.msg = msg;
}

function ResponseData(code, msg, data) {
    //构造方法内的属性和方法
    this.code = code;
    this.msg = msg;
    this.data = data;
}

ResponseData.prototype.ResponseData = function (code, msg) {
    //构造方法内的属性和方法
    this.code = code;
    this.msg = msg;
}

ResponseData.prototype.ResponseData = function (code, msg, data) {
    //构造方法内的属性和方法
    this.code = code;
    this.msg = msg;
    this.data = data;
}


