package com.web.core.utility;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import java.util.HashMap;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-4-8
 * Time: 下午4:57
 * To change this template use File | Settings | File Templates.
 */
public class SMSVerification
{
    private SMSVerification(){};

    private static SMSVerification smsUtil;

    public static SMSVerification getInstance()
    {
        if (null == smsUtil)
        {
            synchronized (SMSVerification.class)
            {
                if (null == smsUtil)
                {
                    smsUtil = new SMSVerification();
                    smsUtil.init();
                }
            }
        }

        return smsUtil;
    }

    private String accountSID = "aaf98f894a85eee5014a9b1207ee0b3b";
    private String authTOKEN = "caca1eb3d57244e79fea82aca1232822";
    private String sandboxHost = "sandboxapp.cloopen.com";
    private String host = "app.cloopen.com";
    private String appId = "8a48b5514a85e618014a9b18788b0abe";
    private final String port = "8883";
    HashMap<String, Object> result = null;
    CCPRestSmsSDK restAPI;

    private void init()
    {
        restAPI = new CCPRestSmsSDK();
        restAPI.init(host, port);
        restAPI.setAccount(accountSID, authTOKEN);
        restAPI.setAppId(appId);
    }

    public String send(String mobilePhone, String validateCode)
    {
        result = restAPI.sendTemplateSMS(mobilePhone, "12544" , new String[] {validateCode});
        String res = "";

        if ("000000".equals(result.get("statusCode")))
        {
            // 正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();

            for(String key:keySet)
            {
                Object object = data.get(key);
                res += key +" = " + object;
                System.out.println(key + " = " + object);
            }
        }
        else
        {
            // 异常返回输出错误码和错误信息
            res += "错误码=" + result.get("statusCode") + " 错误信息=" + result.get("statusMsg");
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息=" + result.get("statusMsg"));
        }

        return res;
    }

    public static void main(String[] args)
    {
        SMSVerification.getInstance().send("18514254415","123454");
        SMSVerification.getInstance().send("18514254415","123453");
    }
}
