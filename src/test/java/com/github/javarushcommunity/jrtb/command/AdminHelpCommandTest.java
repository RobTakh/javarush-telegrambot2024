package com.github.javarushcommunity.jrtb.command;

import static com.github.javarushcommunity.jrtb.command.CommandName.ADMIN_HELP;
import static com.github.javarushcommunity.jrtb.command.AdminHelpCommand.ADMIN_HELP_MESSAGE;

public class AdminHelpCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return ADMIN_HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return ADMIN_HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new AdminHelpCommand(sendBotMessageService);
    }
}
