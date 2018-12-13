package com.epam.javaee.commands;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String JSP_ADD_NEWS = "add-news";
    String JSP_EDIT_NEWS = "edit-news";
    String JSP_NEWS_LIST = "news-list";
    String JSP_NEWS_VIEW = "news-view";
    String NEWS = "news";

    String getResponse(HttpServletRequest request);

    default long getPathVariable(String path, String commandName) {
        String variable = path.substring(path.indexOf(commandName) + commandName.length()).replaceAll("/", "");
        if (variable.isEmpty())
            return 0;
        return Long.parseLong(variable);
    }
}
