/**
 * base64编码
 * @param str
 * @returns {string}
 */
function encodeBase64(str) {

    var enStr = new Buffer(str).toString('base64');
    return enStr;
}

/**
 * base64解码
 * @param enStr
 * @returns {string}
 */
function decodeBase64(enStr) {
    var str = new Buffer(enStr, "base64").toString();
    return str;
}

module.exports.encodeBase64 = encodeBase64;
module.exports.decodeBase64 = decodeBase64;