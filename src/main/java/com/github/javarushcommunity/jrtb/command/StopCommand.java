package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.github.javarushcommunity.jrtb.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE = "Приложение деактивировало все ваши подписки \uD83D\uDE1F. С уважением," +
            "Роберт Тахненко";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }
    @Override
    public void execute(Update update) {
        telegramUserService.findByChatId(update.getMessage().getChatId().toString())
                .ifPresent(active -> {
                    active.setActive(false);
                    telegramUserService.save(active);
                });
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}
