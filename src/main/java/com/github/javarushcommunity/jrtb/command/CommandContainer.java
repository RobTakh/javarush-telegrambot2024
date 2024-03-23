package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.command.annotation.AdminCommand;
import com.github.javarushcommunity.jrtb.javarushclient.JavarushGroupClient;
import com.github.javarushcommunity.jrtb.service.GroupSubService;
import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.github.javarushcommunity.jrtb.service.StatisticService;
import com.github.javarushcommunity.jrtb.service.TelegramUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.github.javarushcommunity.jrtb.command.CommandName.*;

public class CommandContainer {

    private final Map<String, Command> commandHashMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(
            SendBotMessageService sendBotMessageService,
            TelegramUserService telegramUserService,
            JavarushGroupClient javarushGroupClient,
            GroupSubService groupSubService,
            List<String> admins,
            StatisticService statisticService
    ) {
        this.admins = admins;
        commandHashMap = new HashMap<>();
        commandHashMap.put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService));
        commandHashMap.put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService));
        commandHashMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandHashMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));
        commandHashMap.put(STAT.getCommandName(), new StatCommand(sendBotMessageService, statisticService));
        commandHashMap.put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, javarushGroupClient, groupSubService));
        commandHashMap.put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService));
        commandHashMap.put(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(sendBotMessageService, telegramUserService, groupSubService));
        commandHashMap.put(ADMIN_HELP.getCommandName(), new AdminHelpCommand(sendBotMessageService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier, String username) {
        Command orDefault = commandHashMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return Objects.nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}
