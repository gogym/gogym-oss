/*
 * 文件名：EnumPurpose.java 版权：Copyright by www.huawei.com 描述： 修改人：kokJuis 修改时间：2017年8月11日 跟踪单号： 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.enums;

public enum EnumPurpose {

    // 1.头像,2.商品图片、3.商品详情文件
    // 其他类型文件：100

    HEAD_PORTRAIT(1), COMMODITY_IMG(2), COMMODITY_DETAIL(3), OTHER(100);

    private int code;

    private EnumPurpose(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

}
