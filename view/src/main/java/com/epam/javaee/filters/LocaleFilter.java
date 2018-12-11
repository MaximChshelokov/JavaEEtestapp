package com.epam.javaee.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {

    private static final String RUSSIAN = "ru";
    private static final String ENGLISH = "en";
    private static final String USA = "US";
    private static final String RUSSIA = "RU";

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


        String region = USA;

        if (RUSSIAN.equals(localeString)) {
            region = RUSSIA;
        } else {
            localeString = ENGLISH;
        }

        Locale locale = new Locale(localeString, region);
        Config.set(req.getSession(), Config.FMT_LOCALE, locale);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
