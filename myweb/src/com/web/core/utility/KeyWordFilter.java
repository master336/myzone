package com.web.core.utility;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-3-17
 * Time: 上午10:37
 * To change this template use File | Settings | File Templates.
 */
public class KeyWordFilter
{
    private static Pattern pattern = null;

    static
    {
        initPattern();
    }

    // 从words.properties初始化正则表达式字符串
    private static void initPattern()
    {
        StringBuffer patternBuf = new StringBuffer("");

        InputStream in = null;

        try
        {
            in = KeyWordFilter.class.getClassLoader().getResourceAsStream("words.properties");
            Properties pro = new Properties();
            pro.load(in);
            Enumeration enu = pro.propertyNames();
            patternBuf.append("(");

            while (enu.hasMoreElements())
            {
                patternBuf.append((String) enu.nextElement() + "|");
            }

            patternBuf.deleteCharAt(patternBuf.length() - 1);
            patternBuf.append(")");
            pattern = Pattern.compile(new String(patternBuf.toString().getBytes("ISO-8859-1"), "UTF-8"),Pattern.DOTALL);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(in);
        }
    }

    public static String doFilter(String str)
    {
        if (null == str)
        {
            return "";
        }

        Matcher m = pattern.matcher(str);
        str = m.replaceAll("**");
        return str;
    }

    public static void main(String[] args)
    {
        String str = "国敏感词一院学位AV办就敏感词三的报道表示李洪志敏感词二台湾台湾建国运动组织马三中华中华讲清法";
        System.out.println("str:" + str);
        initPattern();
        Date d1 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss:SSS Z");
        System.out.println("start:" + formatter.format(d1));
        System.out.println("共" + str.length() + "个字符，查到" + KeyWordFilter.doFilter(str));
        Date d2 = new Date();
        System.out.println("end:" + formatter.format(d2));
    }

}
