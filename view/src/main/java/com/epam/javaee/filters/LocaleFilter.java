package com.epam.javaee.filters;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

public class LocaleFilter implements Filter {

    public static final String RUSSIAN = "ru";
    public static final String ENGLISH = "en";
    public static final String USA = "US";
    public static final String RUSSIA = "RU";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        String localeString = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("locale".equals(cookie.getName())) {
                    localeString = cookie.getValue();
                }
            }
        }

        if (localeString == null || !(ENGLISH.equals(localeString)
                                      || RUSSIAN.equals(localeString))) {
            localeString = ENGLISH;
        }
        String region = USA;
        if (RUSSIAN.equals(localeString)) {
            region = RUSSIA;
        }
        Locale locale = new Locale(localeString, region);
        Config.set(req.getSession(), Config.FMT_LOCALE, locale);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
