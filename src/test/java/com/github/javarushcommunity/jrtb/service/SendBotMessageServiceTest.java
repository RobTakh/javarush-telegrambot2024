package com.github.javarushcommunity.jrtb.service;

import com.github.javarushcommunity.jrtb.bot.JavarushTelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Test for SendBotMessageService")
public class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private JavarushTelegramBot javarushTelegramBot;

    @BeforeEach
    public void init() {
        javarushTelegramBot = Mockito.mock(JavarushTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(javarushTelegramBot);
    }

    @Test
    public void send_Message_Test() throws TelegramApiException {
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        // when
        sendBotMessageService.sendMessage(chatId, message);

        //then
        Mockito.verify(javarushTelegramBot).execute(sendMessage);
    }
}
