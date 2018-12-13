package com.epam.javaee.commands;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CommandsMapFactory {
    @Inject
    private NewsListCommand newsListCommand;
    @Inject
    private AddNewsCommand addNewsCommand;
    @Inject
    private ViewNewsCommand viewNewsCommand;

    @Produces
    public Map<String, Command> getCommandsMap() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put(newsListCommand.COMMAND_NAME, newsListCommand);
        commandMap.put(addNewsCommand.COMMAND_NAME, addNewsCommand);
        commandMap.put(viewNewsCommand.COMMAND_NAME, viewNewsCommand);
        return commandMap;
    }
}
