/*
 * 文件名：LoggerUtils.java 版权：Copyright by www.huawei.com 描述： 修改人：kokJuis 修改时间：2017年8月9日 跟踪单号： 修改单号：
 * 修改内容：
 */

package pres.gogym.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 日志工具类
 * 
 * @author gogym
 * @version 2017年8月9日
 * @see LoggerUtils
 * @since
 */
public class LoggerUtils
{
    /**
     * 是否开启Debug
     */
    public static boolean isDebug = LogManager.getLogger(LoggerUtils.class).isDebugEnabled();

    public static void info(String message)
    {
        Logger logger = LogManager.getLogger();
        logger.info(message);
    }

    /**
     * Description: info输出
     * 
     * @param clazz
     *            目标.Class
     * @param message
     *            输出信息
     * @see
     */
    public static void info(Class<? extends Object> clazz, String message)
    {
        Logger logger = LogManager.getLogger(clazz);
        logger.info(message);
    }

    /**
     * info 输出
     * 
     * @param clazz
     *            目标.Class
     * @param fmtString
     *            输出信息key
     * @param value
     *            输出信息value
     */
    public static void fmtInfo(Class<? extends Object> clazz, String key, Object... value)
    {

        if (StringUtils.isBlank(key))
        {
            return;
        }
        if (null != value && value.length != 0)
        {
            Logger logger = LogManager.getLogger(clazz);
            logger.info("{}:{}", key, value);
        }
    }

    /**
     * Debug 输出
     * 
     * @param clazz
     *            目标.Class
     * @param message
     *            输出信息
     */
    public static void debug(Class<? extends Object> clazz, String message)
    {
        if (!isDebug) return;
        Logger logger = LogManager.getLogger(clazz);
        logger.debug(message);
    }

    /**
     * Debug 输出
     * 
     * @param clazz
     *            目标.Class
     * @param fmtString
     *            输出信息key
     * @param value
     *            输出信息value
     */
    public static void fmtDebug(Class<? extends Object> clazz, String key, Object... value)
    {
        if (!isDebug) return;

        if (StringUtils.isBlank(key))
        {
            return;
        }
        if (null != value && value.length != 0)
        {
            Logger logger = LogManager.getLogger(clazz);
            logger.debug("{}:{}", key, value);
        }
    }

    /**
     * Error 输出
     * 
     * @param clazz
     *            目标.Class
     * @param message
     *            输出信息
     * @param e
     *            异常类
     */
    public static void error(Class<? extends Object> clazz, String message, Exception e)
    {
        Logger logger = LogManager.getLogger(clazz);
        if (null == e)
        {
            logger.error(message);
            return;
        }
        logger.error(message, e);
    }

    /**
     * Error 输出
     * 
     * @param clazz
     *            目标.Class
     * @param message
     *            输出信息
     */
    public static void error(Class<? extends Object> clazz, String message)
    {
        error(clazz, message, null);
    }

    /**
     * 异常填充值输出
     * 
     * @param clazz
     *            目标.Class
     * @param fmtString
     *            输出信息key
     * @param e
     *            异常类
     * @param value
     *            输出信息value
     */
    public static void fmtError(Class<? extends Object> clazz, Exception e, String key,
                                Object... value)
    {
        if (StringUtils.isBlank(key))
        {
            return;
        }
        if (StringUtils.isBlank(key))
        {
            return;
        }
        if (null != value && value.length != 0)
        {
            Logger logger = LogManager.getLogger(clazz);
            logger.error("{}:{}", key, value);
        }
    }

    /**
     * 异常填充值输出
     * 
     * @param clazz
     *            目标.Class
     * @param fmtString
     *            输出信息key
     * @param value
     *            输出信息value
     */
    public static void fmtError(Class<? extends Object> clazz, String key, Object... value)
    {
        if (StringUtils.isBlank(key))
        {
            return;
        }
        if (StringUtils.isBlank(key))
        {
            return;
        }
        if (null != value && value.length != 0)
        {
            Logger logger = LogManager.getLogger(clazz);
            logger.error("{}:{}", key, value);
        }
    }
}
