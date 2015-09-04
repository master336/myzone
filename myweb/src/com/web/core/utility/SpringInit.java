package com.web.core.utility;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringInit implements ServletContextListener {
private static WebApplicationContext springContext;
private static ServletContext servletContext;

public void contextInitialized(ServletContextEvent event)
{
  springContext = 
    WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

  servletContext = event.getServletContext();
}

public void contextDestroyed(ServletContextEvent event) {
}

public static ApplicationContext getApplicationContext() {
  return springContext;
}

public static Object getBean(String beanId)
{
  return springContext.getBean(beanId);
}

public static ServletContext getServletContext() {
  return servletContext;
}
}
