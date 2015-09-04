package com.web.core.utility;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-4-8
 * Time: 下午7:13
 * To change this template use File | Settings | File Templates.
 */
public class VoiceVerification
{
    private static VoiceVerification verification;
    private String accountSID = "aaf98f894a85eee5014a9b1207ee0b3b";
    private String authTOKEN = "caca1eb3d57244e79fea82aca1232822";
    private String sandboxHost = "sandboxapp.cloopen.com";
    private String host = "app.cloopen.com";
    private String appId = "8a48b5514a85e618014a9b18788b0abe";
    private final String port = "8883";
    HashMap<String, Object> result = null;
    CCPRestSDK restAPI;

    private VoiceVerification()
    {
    }

    public static VoiceVerification getInstance()
    {
        if (null == verification)
        {
            synchronized (VoiceVerification.class)
            {
                if (null == verification)
                {
                    verification = new VoiceVerification();
                    verification.init();
                }
            }
        }

        return verification;
    }

    private void init()
    {
        restAPI = new CCPRestSDK();
        restAPI.init(host, port);
        restAPI.setAccount(accountSID, authTOKEN);
        restAPI.setAppId(appId);
    }

    public String send(String mobilePhone, String validateCode)
    {
        result = restAPI.voiceVerify(validateCode, mobilePhone, "", "3", "");

        if ("000000".equals(result.get("statusCode")))
        {
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();

            for(String key:keySet)
            {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }

            return "";
        }
        else
        {
            // 异常返回输出错误码和错误信息
            String res = "错误码=" + result.get("statusCode") + " 错误信息=" + result.get("statusMsg");
            System.out.println(res);
            return res;
        }
    }
}
