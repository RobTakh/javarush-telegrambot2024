package com.github.javarushcommunity.jrtb.service;

import java.util.List;

/**
 * Service for sending messages via telegramBot
 */
public interface SendBotMessageService {

    /**
     * Send message via telegramBot
     * @param chatId        The chatId, in which messages would be sent.
     * @param message       The message, which would be sent.
     */
    void sendMessage(String chatId, String message);

    /**
     * Send message via telegramBot
     * @param chatId        The chat ID, in which messages would be sent.
     * @param messages      The messages, which would be sent.
     */
    void sendMessage(String chatId, List<String> messages);
}
