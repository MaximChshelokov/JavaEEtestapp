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
    @Inject
    private AddEditNewsCommand addEditNewsCommand;
    @Inject
    private ViewNewsCommand viewNewsCommand;
    @Inject
    private DeleteNewsCommand deleteNewsCommand;

    @Produces
    public Map<String, Command> getCommandsMap() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put(NewsListCommand.COMMAND_NAME, newsListCommand);
        commandMap.put(AddEditNewsCommand.COMMAND_NAME, addEditNewsCommand);
        commandMap.put(ViewNewsCommand.COMMAND_NAME, viewNewsCommand);
        commandMap.put(DeleteNewsCommand.COMMAND_NAME, deleteNewsCommand);
        return commandMap;
    }
}
