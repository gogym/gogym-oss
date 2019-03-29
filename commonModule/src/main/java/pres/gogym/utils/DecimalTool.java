package pres.gogym.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal 工具类
 * <p>
 * 只用于金额计算，所有返回结果都保留2位小数
 * </p>
 */
public class DecimalTool {

    private static final RoundingMode mode = RoundingMode.HALF_UP;

    /**
     * 格式化BigDecimal
     *
     * @param val
     * @return
     */
    public static BigDecimal format(BigDecimal val) {
        if (val == null) {
            return null;
        }
        return val.setScale(2, mode);
    }

    /**
     * 格式化BigDecimal
     * @param val
     * @return
     */
    public static BigDecimal format(Double val){
        if (val == null) {
            return null;
        }
        BigDecimal bd = new BigDecimal(val);
        return bd.setScale(2, mode);
    }

    /**
     * 比较两个BigDecimal是否相等
     *
     * @param val1
     * @param val2
     * @return
     */
    public static boolean equals(BigDecimal val1, BigDecimal val2) {
        if (val1 == null || val2 == null) {
            return false;
        }
        return val1.setScale(2, mode).equals(val2.setScale(2, mode));
    }

    /**
     * 比较两个BigDecimal大小
     * <p>
     * 如果 val1 大于 val2 返回 1
     * 如果 val1 等于 val2 返回 0
     * 如果 val1 小于 val2 返回 -1
     * </p>
     *
     * @param val1
     * @param val2
     * @return
     */
    public static int compare(BigDecimal val1, BigDecimal val2) {
        return val1.setScale(2, mode).compareTo(val2.setScale(2, mode));
    }

    /**
     * 加法
     *
     * @param val1
     * @param val2
     * @return
     */
    public static BigDecimal add(BigDecimal val1, BigDecimal val2) {
        if (val1 == null) {
            val1 = new BigDecimal("0");
        }
        if (val2 == null) {
            val2 = new BigDecimal("0");
        }
        return val1.add(val2).setScale(2, mode);
    }

    /**
     * 减法
     *
     * @param val1
     * @param val2
     * @return
     */
    public static BigDecimal subtract(BigDecimal val1, BigDecimal val2) {
        if (val1 == null) {
            val1 = new BigDecimal("0");
        }
        if (val2 == null) {
            val2 = new BigDecimal("0");
        }
        return val1.subtract(val2).setScale(2, mode);
    }

    /**
     * 乘法
     *
     * @param val
     * @param quantity
     * @return
     */
    public static BigDecimal multiply(BigDecimal val, int quantity) {
        if (val == null) {
            return null;
        }
        return val.multiply(new BigDecimal(quantity)).setScale(2, mode);
    }

    /**
     * 乘法
     *
     * @param val
     * @param discount
     * @return
     */
    public static BigDecimal multiply(BigDecimal val, double discount) {
        if (val == null) {
            return null;
        }
        return val.multiply(new BigDecimal(discount)).setScale(2, mode);
    }

    /**
     * 除法
     * @param val
     * @param val1
     * @return
     */
    public static BigDecimal divide(int val, int val1){
        return divide(new BigDecimal(val), new BigDecimal(val1));
    }

    /**
     * 除法
     * @param val
     * @param val1
     * @return
     */
    public static BigDecimal divide(BigDecimal val, int val1){
        return divide(val, new BigDecimal(val1));
    }

    /**
     * 除法
     * @param val
     * @param val1
     * @return
     */
    public static BigDecimal divide(BigDecimal val, double val1){
        return divide(val, new BigDecimal(val1));
    }

    /**
     * 除法
     * @param val
     * @param val1
     * @return
     */
    public static BigDecimal divide(int val, BigDecimal val1){
        return divide(new BigDecimal(val), val1);
    }

    /**
     * 除法
     * @param val 被除数
     * @param val1 除数
     * @return
     */
    public static BigDecimal divide(BigDecimal val, BigDecimal val1){
        int result = val1.compareTo(BigDecimal.ZERO);
        if(0 == result){
            throw new IllegalArgumentException("除数不能为0...");
        }

       return val.divide(val1, 2, mode);
    }


    public static void main(String[] args) {
        // System.out.println(weightTonFormat(234.5673));
        System.out.println(multiply(new BigDecimal(100), 3));
    }
}
