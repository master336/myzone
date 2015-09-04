package com.web.core.utility;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-5-5
 * Time: 下午8:26
 * To change this template use File | Settings | File Templates.
 */
public class TencentMailUtility
{
    public static void send(String to, String subject, String body) throws MailException
    {
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.port", "465");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.ssl", "true");

        Session session = null;
        Transport transport = null;
        Message message = null;

        try
        {
            session = Session.getDefaultInstance(prop);

            // 为了显示发送的细节设置debug模式
            session.setDebug(true);

            // 创建一封邮件(主题,正文内容,邮件的发送地址)
            message = new MimeMessage(session);
            //message.setSubject(MimeUtility.encodeText(subject, "gb2312", "B"));
            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            message.setSubject("=?GB2312?B?" + enc.encode(subject.getBytes()) + "?=");
            message.setContent(body, "text/html;charset=UTF-8");

            // 将原地址设置到消息的信息中
            message.setFrom(new InternetAddress("admin@xinwuban.com"));

            // 创建一个邮件的传输对象
            transport = session.getTransport();

            // 配置连接信息（发送服务器地址，？，？）smtp.exmail.qq.com   smtp.exmail.qq.com
            transport.connect("smtp.exmail.qq.com", "admin@xinwuban.com", "58372721990cd");

            // 配置收件人的地址(可以多个)
            transport.sendMessage(message, new Address[] {new InternetAddress(to)});
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            // 关闭资源
            try
            {
                transport.close();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static void send(String from, String password, String to, String subject, String body) throws MailException
    {
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.port", "465");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.ssl", "true");

        Session session = null;
        Transport transport = null;
        Message message = null;

        try
        {
            session = Session.getDefaultInstance(prop);

            // 为了显示发送的细节设置debug模式
            session.setDebug(true);

            // 创建一封邮件(主题,正文内容,邮件的发送地址)
            message = new MimeMessage(session);
            //message.setSubject(subject);
            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            message.setSubject("=?GB2312?B?" + enc.encode(subject.getBytes()) + "?=");
            //message.setSubject(MimeUtility.encodeText(subject, "gb2312", "B"));
            message.setContent(body, "text/html;charset=UTF-8");

            // 将原地址设置到消息的信息中
            message.setFrom(new InternetAddress(from));

            // 创建一个邮件的传输对象
            transport = session.getTransport();

            // 配置连接信息（发送服务器地址，？，？）smtp.exmail.qq.com   smtp.exmail.qq.com
            transport.connect("smtp.exmail.qq.com", from, password);

            // 配置收件人的地址(可以多个)
            transport.sendMessage(message, new Address[] {new InternetAddress(to)});
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            // 关闭资源
            try
            {
                transport.close();
            }
            catch (Exception e)
            {
                throw new RuntimeException(e.getMessage());
            }
        }

    }

    private static String encodeStringToGBK(String content)
    {
        try
        {
            byte[] temp = content.getBytes("UTF-8");
            return new String(temp, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return content;
    }

}
