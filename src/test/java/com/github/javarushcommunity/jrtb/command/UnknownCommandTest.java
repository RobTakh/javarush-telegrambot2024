package com.github.javarushcommunity.jrtb.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.javarushcommunity.jrtb.command.UnknownCommand.UNKNOWN_MESSAGE;

@DisplayName("Test for UnknownCommand")
public class UnknownCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return "/fghjkl";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}
