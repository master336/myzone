package com.web.web.filter;

import com.web.web.common.SessionUser;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Edmund on 2015/7/13.
 */
public class HasLoginFilter  implements Filter
{
    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUrl = request.getRequestURI();

        //��ʼ��һ�� ���� �ù�����������
        String pages[] = new String[] {
                "/index.do",
                "/login",
                "/logout",
                "/register",
                "/area/",
                "/member/",
                "/memberlevel",
                "/product/",
                "/productcat/",
                "/producteva/",
                "/productreq/",
                "/productsup/",
                "/shop/",
                "/shoplevel/",
                "/shopmsg/",
                "/sysuser/",
                "/mngr/",
        };

        boolean gateWay = false;

        for(int pageIndex = 0; pageIndex < pages.length; pageIndex++)
        {
            if (StringUtils.hasLength(requestUrl) && requestUrl.contains(pages[pageIndex]))
            {
                gateWay = true;
                break;
            }
        }

        // ȡ�õ�ǰ����Ĳ���
        Map map = request.getParameterMap();
        String param = "";
        String remark = "";

        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
        {
            String key = (String) iterator.next();
            String value = ((String []) map.get(key))[0];

            if ((!requestUrl.equals("/register.do")) && key.equals("__remark__"))
            {
                remark = URLEncoder.encode(value, "UTF-8");
                continue;
            }

            try
            {
                value = URLEncoder.encode(value, "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }

            if (param.trim().length() == 0)
            {
                param = "?" + key + "=" + value;
            }
            else
            {
                param = param + "&" + key + "=" + value;
            }
        }

        if (org.apache.commons.lang.StringUtils.isNotBlank(param))
        {
            requestUrl =  requestUrl + param;
        }

        /***检查session中的登录状态*/
         SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");

        // �����ѵ�¼�û���gateway���͵���������Ķ�Ҫ������½ҳ
        if (sessionUser != null || gateWay)
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            // ��ʱ����Ҫע��
            // response.sendRedirect("/register.html?__remark__=" + remark + "&forwardUrl=" + requestUrl);
        }
        if (request.getRequestURL().indexOf("/ajax/") != -1)
        {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
        }
    }

    public void destroy()
    {
    }

}
