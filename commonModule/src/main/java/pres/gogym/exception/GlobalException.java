/*
 * 文件名：GlobalException.java 版权：Copyright by www.poly.com 描述： 修改人：gogym 修改时间：2017年12月14日 跟踪单号： 修改单号：
 * 修改内容：
 */

package pres.gogym.exception;

public class GlobalException extends RuntimeException 
{

    /**
     * 意义，目的和功能，以及被用到的地方
     */

    private static final long serialVersionUID = 1L;

    private int code;

    public void setCode(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public GlobalException(String message)
    {
        super(message);
    }

    public GlobalException(String message, int code)
    {
        super(message);
        this.code = code;
    }
}
