package com.web.core.utility;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-3-17
 * Time: 上午10:46
 * To change this template use File | Settings | File Templates.
 */
public class Setting implements Serializable
{
    private static final Log log = LogFactory.getLog(Setting.class);
    public static Properties SETTINGS;

    static
    {
        init();
    }

    private static void init()
    {
        SETTINGS = new Properties();
        InputStream stream = Setting.class.getClassLoader().getResourceAsStream("setting.properties");

        try
        {
            SETTINGS.load(stream);
        }
        catch (IOException e)
        {
            log.error("初始化配置失败！",e);
        }
        finally
        {
            IOUtils.closeQuietly(stream);
        }
    }

    public static String getSetting(String key)
    {
        return SETTINGS.getProperty(key);
    }
}
