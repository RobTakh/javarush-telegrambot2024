package com.github.javarushcommunity.jrtb.service;

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
}
