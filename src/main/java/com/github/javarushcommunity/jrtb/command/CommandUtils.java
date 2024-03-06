package com.github.javarushcommunity.jrtb.command;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Utils class for Commands.
 */
public class CommandUtils {

    /**
     * Retrieve chatId from {@link Update} object.
     *
     * @param update        {@link Update}
     * @return              ChatId from {@link Update} object
     */
    public static String getChatId(Update update) {
        return update.getMessage().getChatId().toString();
    }

    /**
     * Retrieve text of the message from {@link Update} object.
     *
     * @param update        {@link Update}
     * @return              The text of the message from the {@link Update} object
     */
    public static String getMessage(Update update) {
        return update.getMessage().getText();
    }
}
