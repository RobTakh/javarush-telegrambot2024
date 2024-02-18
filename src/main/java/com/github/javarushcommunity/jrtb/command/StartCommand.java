package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String START_MESSAGE =
            "Привет. Я приложение, созданное Робертом Тахненко - Javarush Telegram Bot, которое будет помогать клиентам" +
                    "(в том числе и тебе) быть в курсе последних статей тех авторов(источников), которые могут " +
                    "быть интересны. На данном этапе, приложение только формируется и будет дорабатываться";

    /**
     * Здесь не добавляем сервис через получение из ApplicationContext, потому что если это сделать так,
     * то будет циклическая зависимость, которая ломает работу приложения.
     */
    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
