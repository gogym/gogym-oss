package pres.gogym.utils;


/**
 * 发送企业号消息的类
 *
 * @author LJ
 * @version 2017年10月9日
 * @see EnterprizeUtil
 * @since
 */
public class EnterprizeUtil
{

    public static final String CORIPD = "wx36777fc9fa7b1b6f";// 企业Id

    public static final String CORPSECRET = "NdSyIeEaaSobYo9RkbCJ7xKbMqriWS8z4hfdaRzcbPQ";// 管理组的凭证密钥

    public static final String EDIT_CORPSECRET = "uGgdxoy0tH_3TV8wURKGqhQa540sVspmyB3aBTlLGPw";// 企业号通讯录安全密钥

    public static final String PARENTID = "1";// 通讯录父节点

    public static final String AGENTID = "0";// 应用ID

    public static final String AUTHORIZATIONURL = "https://open.weixin.qq.com/connect/oauth2/authorize";// 微信授权URL

    public static final String TOKENURL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";// 获取accessToken的url

    public static final String CONTACTLISTURL = "https://qyapi.weixin.qq.com/cgi-bin/department/list";// 通讯录列表url

    public static final String CONTACTUPDATEURL = "https://qyapi.weixin.qq.com/cgi-bin/department/update";// 更新通讯录url

    public static final String CONTACTCREATEURL = "https://qyapi.weixin.qq.com/cgi-bin/department/create";// 新增通讯录url

    public static final String CONTACTDELETEURL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete";// 删除通讯录url

    public static final String MENBERLISTURL = "https://qyapi.weixin.qq.com/cgi-bin/user/list";// 成员列表rul

    public static final String MENBERCREAETURL = "https://qyapi.weixin.qq.com/cgi-bin/user/create";// 更新成员url

    public static final String MENBERDELETEURL = "https://qyapi.weixin.qq.com/cgi-bin/user/delete";// 删除成员url

    public static final String MENBERDETAIL = "https://qyapi.weixin.qq.com/cgi-bin/user/get";// 成员详情url

    public static final String MENBERUPDATEURL = "https://qyapi.weixin.qq.com/cgi-bin/user/update";// 更新成员url

    public static final String LABELLISTURL = "https://qyapi.weixin.qq.com/cgi-bin/tag/list";// 标签列表url

    public static final String LABELADDURL = "https://qyapi.weixin.qq.com/cgi-bin/tag/create";// 新增标签url

    public static final String LABELDELETEURL = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete";// 删除标签url

    public static final String LABELUPDATEURL = "https://qyapi.weixin.qq.com/cgi-bin/tag/update";// 更新标签url

    public static final String CONTACTGETURL = "https://qyapi.weixin.qq.com/cgi-bin/tag/get";// 获取标签的通讯录url

    public static final String LABELADDCONTACTURL = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers";// 添加标签通讯录url

    public static final String LABELDELETECONTACTURL = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers";// 删除标签通讯录url

    public static final String SENURL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";// 发送消息url

    public static final String GETUSERURL = "https://qyapi.weixin.qq.com/cgi-bin/user/get";// 获取成员url

    public static final String XDDTS = "新订单提示";

    public static final String YCJHTS = "延迟拣货提示";

    public static final String YCPSTS = "延迟配送提示";

    public static final String WWCPSTS = "未完成配送提示";

    public static final String DDWWCTS = "订单未完成提示";

    public static final String XTHDTS2MD = "新退货单提示-门店";

    public static final String XTKDTS2CW = "新退货单提示-财务";

    public static final String XTKDTS2SH = "新退货单提示-商户";

    public static final String WCLTHDTS2MD = "未处理退款单提示-门店";

    public static final String WCLTHDTS2CW = "未处理退货单提示-财务";

    public static final String CSTHDCLTS = "超时退款单处理提示";

    public static final String XHWSSTS = "小黑屋申诉提示";

    public static final String HYWCLTS = "黄页未处理提示";

    public static final String TSWCLTS = "投诉未处理提示";

    public static final String PT_XDDTS = "团购—新订单提示";

    public static final String PT_YCJHTS = "团购—延迟拣货提示";

    public static final String PT_YCPSTS = "团购—延迟配送提示";

    public static final String PT_WWCPS = "团购—未完成配送";

    public static final String PT_DDWWC = "团购—订单未完成";

    /**
     * 获取accessToken，且将其放入session
     *
     * @return
     */
 /*   public static String getAccessToken(HttpServletRequest request)
    {
        String token = request.getHeader("Token");
        String accessToken = null;
        if (StringUtil.isEmpty(token))
        {
            Map<String, Object> params = new HashMap<>();
            params.put("corpid", CORIPD);
            params.put("corpsecret", CORPSECRET);
            String result = new HttpHelper().sendGetHttp(TOKENURL, params);// 获取企业号AccessToken
            JSONObject json = new JSONObject(result);

            accessToken = json.getString("access_token");
        }
        return accessToken;
    }

    *//**
     * 获取通讯录列表
     *
     * @return
     *//*
    public static List<Contact> getContactList(String accessToken)
    {

        List<Contact> contactList = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", accessToken);
        String result = new HttpHelper().sendGetHttp(CONTACTLISTURL, params);
        JSONObject json = new JSONObject(result);

        if ("ok".equals(json.getString("errmsg")))
        {
            JSONArray departments = json.getJSONArray("department");
            if (departments != null && departments.length() > 0)
            {
                for (int i = 0; i < departments.length(); i++ )
                {
                    Contact contact = new Contact();
                    contact.setId(((JSONObject)departments.get(i)).getString("id"));
                    contact.setName(((JSONObject)departments.get(i)).getString("name"));
                    if (i != 0)
                    {
                        contactList.add(contact);// 将第一级全部去掉
                    }
                }
            }
        }
        return contactList;
    }
*/
}
