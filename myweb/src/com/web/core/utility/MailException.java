package com.web.core.utility;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-5-5
 * Time: 下午8:27
 * To change this template use File | Settings | File Templates.
 */
public class MailException extends Exception
{
    public MailException()
    {
        super();
    }

    public MailException(String message)
    {
        super(message);
    }

    public MailException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
