package com.epam.javaee.commands;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class CommandsMapFactory {
    @Inject
    private NewsListCommand newsListCommand;

    @Produces
    public Map<String, Command> getCommandsMap() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("news-list", newsListCommand);
        return commandMap;
    }
}
