package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.javarushclient.JavarushGroupClient;
import com.github.javarushcommunity.jrtb.service.GroupSubService;
import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.github.javarushcommunity.jrtb.service.TelegramUserService;

import java.util.HashMap;
import java.util.Map;

import static com.github.javarushcommunity.jrtb.command.CommandName.*;

public class CommandContainer {

    private final Map<String, Command> commandHashMap;
    private final Command unknownCommand;

    public CommandContainer(
            SendBotMessageService sendBotMessageService,
            TelegramUserService telegramUserService,
            JavarushGroupClient javarushGroupClient,
            GroupSubService groupSubService
    ) {
        commandHashMap = new HashMap<>();
        commandHashMap.put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService));
        commandHashMap.put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService));
        commandHashMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandHashMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));
        commandHashMap.put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService));
        commandHashMap.put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, javarushGroupClient, groupSubService));
        commandHashMap.put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandHashMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
