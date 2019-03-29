var crypto = require('crypto');

//密钥
var secretKey = "209f04ad11cafd80bf2d4fd66665a19c";

/**
 * aes加密
 * @param data
 * @param secretKey
 */
function aesEncrypt(data) {
    var cipher = crypto.createCipher('aes-128-ecb', secretKey);
    return cipher.update(data, 'utf8', 'base64') + cipher.final('base64');
}

/**
 * aes解密
 * @param data
 * @param secretKey
 * @returns {*}
 */
function aesDecrypt(data) {
    var cipher = crypto.createDecipher('aes-128-ecb', secretKey);
    return cipher.update(data, 'base64', 'utf8') + cipher.final('utf8');
}


module.exports.aesEncrypt = aesEncrypt;
module.exports.aesDecrypt = aesDecrypt;