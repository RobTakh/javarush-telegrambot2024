package com.github.javarushcommunity.jrtb.command;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Command interface for handling Robert's telegramBot commands
 *
 * I use the pattern Command for designing the architecture of telegramBot's command.
 * See the article with the logic of this pattern: https://javarush.com/groups/posts/1684-pattern-command-svoimi-slovami
 */
public interface Command {

    /**
     * Main method, which is executing command logic
     * @param update     provided {@link Update} object with all the needed data for command.
     */
    void execute(Update update);
}
