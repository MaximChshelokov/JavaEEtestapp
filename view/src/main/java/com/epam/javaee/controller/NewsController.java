package com.epam.javaee.controller;

import com.epam.javaee.commands.Command;
import com.epam.javaee.commands.CommandFactory;
import org.slf4j.Logger;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/action/*")
public class NewsController extends HttpServlet {

    @Inject
    private CommandFactory commandFactory;
    @Inject
    private transient Logger log;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = getCommandName(req.getPathInfo());
        log.info(String.format("Request url: %s, Command invoked: %s", req.getRequestURI(), commandName));
        Command command = commandFactory.getCommand(commandName);
        if (command != null) {
            req.getRequestDispatcher(getJspPath(command.getResponse(req))).forward(req, resp);
        } else {
            resp.sendError(resp.SC_NOT_FOUND);
        }
    }

    private String getCommandName(String requestUri) {
        String[] pathElements = requestUri.split("/");
        return pathElements[pathElements.length - 1];
    }

    private String getJspPath(String jspName) {
        return String.format("/WEB-INF/jsp/%s.jsp", jspName);
    }
}
