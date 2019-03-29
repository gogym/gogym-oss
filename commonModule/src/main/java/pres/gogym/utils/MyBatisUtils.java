/**
 * 
 */
package pres.gogym.utils;

/**
 * @author gogym
 * @version 1.0
 */
public class MyBatisUtils
{

    private static String getPackageName(Class<?> clzss)
    {
        return clzss.getPackage().getName();
    }

    public static String getSqlKey(Class<?> clzss, String shortKey)
    {
        return getPackageName(clzss).concat(".").concat(clzss.getSimpleName()).concat(".").concat(
            shortKey);
    }
}
