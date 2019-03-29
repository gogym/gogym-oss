/*
 * 文件名：EnumSuffix.java 版权：Copyright by www.huawei.com 描述： 修改人：kokJuis 修改时间：2017年8月11日 跟踪单号： 修改单号：
 * 修改内容：
 */

package pres.gogym.dfs.enums;

public enum EnumFileType {

    // 文件类型 1、图片 2、文本 、3、音频 4、视频 5、压缩文件 100、未识别

    IMG(1), TEXT(2), AUDIO(3), VIDEO(4), ZIP(5), HTML(6), OTHER(100);

    private int code;

    private EnumFileType(int code)
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
