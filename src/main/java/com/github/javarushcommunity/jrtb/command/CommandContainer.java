package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.service.SendBotMessageService;

import java.util.HashMap;
import java.util.Map;

import static com.github.javarushcommunity.jrtb.command.CommandName.*;

public class CommandContainer {

    private final Map<String, Command> commandHashMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandHashMap = new HashMap<>();
        commandHashMap.put(START.getCommandName(), new StartCommand(sendBotMessageService));
        commandHashMap.put(STOP.getCommandName(), new StopCommand(sendBotMessageService));
        commandHashMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandHashMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandHashMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
