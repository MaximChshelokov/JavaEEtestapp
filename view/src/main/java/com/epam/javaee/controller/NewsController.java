package com.epam.javaee.controller;

import com.epam.javaee.commands.Command;
import com.epam.javaee.commands.CommandFactory;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

@WebServlet("/action/*")
public class NewsController extends HttpServlet {

    @Inject
    private CommandFactory commandFactory;
    @Inject
    private transient Logger log;
    private static final String REDIRECTION = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        String commandName = getCommandName(req.getPathInfo());
        log.info("Request url: {}, Command invoked: {}", req.getRequestURI(), commandName);
        Command command = commandFactory.getCommand(commandName);

        ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);

        if (command == null) {
            log.error("Command not found: {}", commandName);
            resp.sendError(resp.SC_NOT_FOUND);
            return;
        }

        String response = command.getResponse(req);

        if (response == null) {
            log.error("Request url is incorrect: {}", req.getPathInfo());
            resp.sendError(resp.SC_BAD_REQUEST);
            return;
        }

        if (response.startsWith(REDIRECTION)) {
            resp.sendRedirect(req.getContextPath() + response.replace(REDIRECTION, ""));
        } else {
            req.getRequestDispatcher(getJspPath(response)).forward(req, resp);
        }

    }

    private String getJspPath(String jspName) {
        return String.format("/WEB-INF/jsp/%s.jsp", jspName);
    }

    /**
     * Extract a command name from the path by removing path variables after the command name. Also
     * remove slashes
     *
     * @param path Servlet path
     * @return Command name
     */
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
