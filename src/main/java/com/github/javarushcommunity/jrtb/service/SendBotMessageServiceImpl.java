package com.github.javarushcommunity.jrtb.service;

import com.github.javarushcommunity.jrtb.bot.JavarushTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final JavarushTelegramBot javarushTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(JavarushTelegramBot javarushTelegramBot) {
        this.javarushTelegramBot = javarushTelegramBot;
    }
    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            javarushTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            // Will format this place due to increase of my project
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String chatId, List<String> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        messages.forEach(m -> sendMessage(chatId, m));
    }
}
