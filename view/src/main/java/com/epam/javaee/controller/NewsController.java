package com.epam.javaee.controller;

import com.epam.javaee.commands.Command;
import com.epam.javaee.commands.CommandFactory;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/action/*")
public class NewsController extends HttpServlet {

    @Inject
    private CommandFactory commandFactory;
    @Inject
    private transient Logger log;

    @Override
    public void init() throws ServletException {
        super.init();
        DateTimeConverter dtConverter = new DateConverter();
        dtConverter.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dtConverter, Date.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getPathInfo().replace("/", "");
        log.info("Request url: {}, Command invoked: {}", req.getRequestURI(), commandName);
        Command command = commandFactory.getCommand(commandName);
        if (command != null) {
            String response = command.getResponse(req);
            if (response != null) {
                req.getRequestDispatcher(getJspPath(response)).forward(req, resp);
            } else {
                log.warn("Wrong method: %s", req.getMethod());
                resp.sendError(resp.SC_METHOD_NOT_ALLOWED);
            }
        } else {
            log.error("Command not found");
            resp.sendError(resp.SC_NOT_FOUND);
        }
    }

    private String getJspPath(String jspName) {
        return String.format("/WEB-INF/jsp/%s.jsp", jspName);
    }
}
