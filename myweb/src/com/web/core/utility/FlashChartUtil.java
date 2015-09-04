package com.web.core.utility;

import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-3-17
 * Time: 上午10:31
 * To change this template use File | Settings | File Templates.
 */
public class FlashChartUtil
{
    public static String getColor(String placeName)
    {
        MD5 md = new MD5();
        String mStr = md.getMD5ofStr(placeName);
        return "#" + StringUtils.substring(mStr, 0, 6);
    }

    public static String getServer(HttpServletRequest request) {
        String server = "http://" + request.getServerName();
        int port = request.getServerPort();
        if (port != 80) {
            server = server + ":" + port;
        }
        return server;
    }

}
