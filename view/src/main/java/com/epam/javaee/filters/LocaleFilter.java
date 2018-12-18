package com.epam.javaee.filters;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

@WebFilter("/*")
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
        String localeString = req.getParameter("lang");
        String region = USA;

        if (RUSSIAN.equals(localeString)) {
            region = RUSSIA;
        } else {
            localeString = ENGLISH;
        }

        Locale locale = new Locale(localeString);
        Config.set(req.getSession(), Config.FMT_LOCALE, locale);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
