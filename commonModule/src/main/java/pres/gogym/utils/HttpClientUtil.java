package pres.gogym.utils;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * 网络连接工具类
 * 
 * @author gogym
 * @date 2014年12月29日
 */
public class HttpClientUtil
{
    private static final String TAG = "HttpClientUtil";

    private static final String CHARSET = "UTF-8";

    private static HttpURLConnection uRLConnection;

    private HttpClientUtil()
    {

    }

    // 通过httpURLConnection访问
    private static String httpURLConnection(String urlAddress, String param)
    {

        String response = "";

        try
        {
            URL url = new URL(urlAddress);
            uRLConnection = (HttpURLConnection)url.openConnection();
            uRLConnection.setDoInput(true);
            uRLConnection.setDoOutput(true);
            uRLConnection.setRequestMethod("POST");
            uRLConnection.setUseCaches(false);
            uRLConnection.setInstanceFollowRedirects(false);
            uRLConnection.setRequestProperty("Accept-Charset", CHARSET);
            uRLConnection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded;charset=" + CHARSET);
            uRLConnection.setConnectTimeout(10000);

            uRLConnection.connect();

            if ("" != param)
            {
                DataOutputStream out = new DataOutputStream(uRLConnection.getOutputStream());
                out.write(param.getBytes("UTF-8"));
                out.flush();
                out.close();
            }
            if (uRLConnection.getResponseCode() == 200)
            {}
            else
            {}

            InputStream is = uRLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, CHARSET));

            String readLine = null;
            while ((readLine = br.readLine()) != null)
            {
                // response = br.readLine();
                response = readLine;
            }

            is.close();
            br.close();
            uRLConnection.disconnect();

        }
        catch (Exception e)
        {}

        return response;

    }

    // 不需要缓存数据
    public static String getHttpURLConnection(String urlAddress, Map<String, String> params)
    {

        String response = "";
        // 请求参数
        String param = "";
        if (params != null)
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                param += entry.getKey() + "=" + entry.getValue() + "&";
            }
            urlAddress = urlAddress + "?"
                         + param.replace(param.substring(0, param.length() - 1), "");
        }

        response = HttpClientUtil.httpURLConnection(urlAddress, param);

        return response;
    }

    // 不需要缓存数据
    public static String postHttpURLConnection(String urlAddress, Map<String, String> params)
    {

        String response = "";
        // 请求参数
        String param = "";
        if (params != null)
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                param += entry.getKey() + "=" + entry.getValue() + "&";
            }
            urlAddress = urlAddress + "?" + param.substring(0, param.length() - 1);
        }

        response = HttpClientUtil.httpURLConnection(urlAddress, param);

        return response;
    }

    /**
     * 发送json数据
     * 
     * @param urlAddress
     * @param json
     * @return
     */
    public static String doPost(String urlAddress, String json)
    {
        String response = HttpClientUtil.httpURLConnection(urlAddress, json);
        return response;
    }

}
