package com.epam.javaee.controller;

import com.epam.javaee.commands.Command;
import com.epam.javaee.commands.CommandFactory;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/action/*")
public class NewsController extends HttpServlet {

    @Inject
    private CommandFactory commandFactory;
    @Inject
    private transient Logger log;
    private static final String REDIRECTION = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = getCommandName(req.getPathInfo());
        log.info("Request url: {}, Command invoked: {}", req.getRequestURI(), commandName);
        Command command = commandFactory.getCommand(commandName);
        if (command != null) {
            String response = command.getResponse(req);
            if (response != null) {
                if (response.startsWith(REDIRECTION)) {
                    resp.sendRedirect(req.getContextPath() + response.replace(REDIRECTION, ""));
                } else {
                    req.getRequestDispatcher(getJspPath(response)).forward(req, resp);
                }
            } else {
                log.warn("Wrong method: {}", req.getMethod());
                resp.sendError(resp.SC_METHOD_NOT_ALLOWED);
            }
        } else {
            log.error("Command not found: {}", commandName);
            resp.sendError(resp.SC_NOT_FOUND);
        }
    }

    private String getJspPath(String jspName) {
        return String.format("/WEB-INF/jsp/%s.jsp", jspName);
    }

    private String getCommandName(String path) {
        String commandName = path.replaceFirst("/", "");
        int secondSlashIndex = commandName.indexOf('/');
        if (secondSlashIndex == -1) {
            return commandName;
        } else {
            return commandName.substring(0, secondSlashIndex);
        }
    }
}
