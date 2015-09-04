package com.web.web.listener;

import com.web.web.common.WebContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Edmund on 2015/7/13.
 */
public class StartListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent event)
    {
        try
        {
            ServletContext application = event.getServletContext();
            WebContext.servletContext = application;
            WebContext.context = WebApplicationContextUtils.getWebApplicationContext(application);
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent event)
    {
        System.exit(-1);
    }
}
