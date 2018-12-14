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
    @Inject
    private DeleteNewsCommand deleteNewsCommand;

    @Produces
    public Map<String, Command> getCommandsMap() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put(NewsListCommand.COMMAND_NAME, newsListCommand);
        commandMap.put(AddNewsCommand.COMMAND_NAME, addNewsCommand);
        commandMap.put(ViewNewsCommand.COMMAND_NAME, viewNewsCommand);
        commandMap.put(DeleteNewsCommand.COMMAND_NAME, deleteNewsCommand);
        return commandMap;
    }
}
