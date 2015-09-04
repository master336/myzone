package com.web.core.utility;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Edmund
 * Date: 15-4-9
 * Time: 下午7:12
 * To change this template use File | Settings | File Templates.
 */
public class CookieUtils
{
    public static Cookie getCookie(Cookie cookies[], String name)
    {
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals(name))
                {
                    return cookie;
                }
            }
        }

        // we've got this far so the specified cookie wasn't found
        return null;
    }

    /**
     * Gets a reference to a named cookie from an array of cookies.
     *
     * @param name    the name of the cookie to find
     * @param request HttpServletRequest
     * @return a reference to a Cookie, or null if the cookie couldn't be found
     */
    public static Cookie getCookie(HttpServletRequest request, String name)
    {
        Cookie[] cookies = request.getCookies();
        return getCookie(cookies, name);
    }
    /**
     * Adds a cookie with the specified name, value and expiry.
     *
     * @param response the HttpServletResponse to add the cookie to
     * @param name     the name of the cookie
     * @param value    the value of the cookie
     * @param maxAge   the maxAge of the cookie (in seconds)
     * @param domain   for example: .51void.com
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String domain)
    {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");

        if (maxAge != 0)
        {
            cookie.setMaxAge(maxAge);
        }

        if (StringUtils.isNotBlank(domain))
        {
            cookie.setDomain(domain);
        }

        response.addCookie(cookie);
    }

    /**
     * Removes a cookie with the specified name.
     *
     * @param response the HttpServletResponse to remove the cookie from
     * @param name     the name of the cookie
     * @param domain   for example: .51void.com
     */
    public static void removeCookie(HttpServletResponse response, String name, String domain)
    {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);

        if (StringUtils.isNotBlank(domain))
        {
            cookie.setDomain(domain);
        }

        response.addCookie(cookie);
    }

}
