package com.epam.javaee.commands;

import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CommandFactory {

    @Inject
    private Map<String, Command> commandMap;

    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}
