package com.web.web.common;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Edmund on 2015/7/13.
 */
public class WebContext
{
    public static WebApplicationContext context;
    public static ServletContext servletContext;

    public static Object getBean(String beanName)
    {
        if (context == null)
        {
            return null;
        }
        else
        {
            return context.getBean(beanName);
        }
    }

    // ���� SessionUser
    public static SessionUser getSessionUser(HttpServletRequest request)
    {
        SessionUser sessionUser = (SessionUser) WebUtils.getSessionAttribute(request, "sessionUser");
        return sessionUser;
    }

}
