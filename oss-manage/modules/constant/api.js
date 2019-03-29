//这个类用来定义调用后台api的常量，下面给出的是示例

var constants = require('../constant/constants');

module.exports = Object.freeze({
    /**
     * 系统用户管理
     */
    LOGIN: constants.DOMAIN + 'ossDeveloper/developerLogin',
    REGISTER: constants.DOMAIN + 'ossDeveloper/developerRegister',
    ADDAPP: constants.DOMAIN + 'ossAppInfo/addOssApp',
    APPLIST: constants.DOMAIN + 'ossAppInfo/findAppByDev',
    DELAPP: constants.DOMAIN + 'ossAppInfo/delById',
    UPDATEAPP: constants.DOMAIN + 'ossAppInfo/updateOssApp',
    FINDAPP: constants.DOMAIN + 'ossAppInfo/findOssApp',
    UPDATAOSSUSER: constants.DOMAIN + 'ossDeveloper/updateOssDeveloper',
    COUNTDATA: constants.DOMAIN + 'home/countData',
    GEFILE: constants.DOMAIN + 'ossMaterialInfo/getFile/',
    DOWNFILE: constants.DOMAIN + 'ossMaterialInfo/downFile/',
    GETSTATISTICS: constants.DOMAIN + "home/findStatistics",
    STATISTICSAPI: constants.DOMAIN + 'home/statisticsApi',
    UPDATESECRET: constants.DOMAIN + 'ossAppInfo/updateAppSecret',
    FINDFILELIST: constants.DOMAIN + 'ossMaterialInfo/findList',
    DELFILE: constants.DOMAIN + 'ossMaterialInfo/delOne'

});