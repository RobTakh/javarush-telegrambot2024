package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.javarushclient.JavarushGroupClient;
import com.github.javarushcommunity.jrtb.service.GroupSubService;
import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.github.javarushcommunity.jrtb.service.StatisticService;
import com.github.javarushcommunity.jrtb.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

@DisplayName("Test for CommandContainer")
public class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        JavarushGroupClient javarushGroupClient = Mockito.mock(JavarushGroupClient.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        StatisticService statisticService = Mockito.mock(StatisticService.class);
        commandContainer = new CommandContainer(
                sendBotMessageService,
                telegramUserService,
                javarushGroupClient,
                groupSubService,
                Collections.singletonList("username"),
                statisticService
        );
    }

    @Test
    public void get_All_The_Existing_Commnads_Test() {
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName(), "username");
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void get_Return_Unknown_Command_Test() {
        String unknownCommand = "/hiuhiuhoi";

        Command command = commandContainer.retrieveCommand(unknownCommand, "username");

        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
