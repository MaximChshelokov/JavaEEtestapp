package com.epam.javaee.listeners;

import com.epam.javaee.util.MenuListLoader;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

@WebListener
public class ApplicationInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DateTimeConverter dtConverter = new DateConverter();
        dtConverter.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dtConverter, Date.class);
        ServletContext context = servletContextEvent.getServletContext();
        String fileName = context.getRealPath("/WEB-INF/menu.txt");
        context.setAttribute("navigationMenu",
                new MenuListLoader(fileName).getMenuList());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
