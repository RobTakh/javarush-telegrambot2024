package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.repository.entity.GroupSub;
import com.github.javarushcommunity.jrtb.repository.entity.TelegramUser;
import com.github.javarushcommunity.jrtb.service.GroupSubService;
import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.github.javarushcommunity.jrtb.service.TelegramUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.Optional;

import static com.github.javarushcommunity.jrtb.command.AbstractCommandTest.*;
import static com.github.javarushcommunity.jrtb.command.CommandName.DELETE_GROUP_SUB;

@DisplayName("Unit-tests for DeleteGroupSubCommand")
public class DeleteGroupSubCommandTest {
    /*private Command command;
    private SendBotMessageService sendBotMessageService;
    GroupSubService groupSubService;
    TelegramUserService telegramUserService;

    @BeforeEach
    public void init() {
        sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        groupSubService = Mockito.mock(GroupSubService.class);
        telegramUserService = Mockito.mock(TelegramUserService.class);

        command = new DeleteGroupSubCommand(sendBotMessageService, telegramUserService, groupSubService);
    }

    @Test
    public void return_Empty_Subscription_List_Test() {
        Long chatId = 12345L;
        Update update = prepareUpdate(chatId, DELETE_GROUP_SUB.getCommandName());

        Mockito.when(telegramUserService.findByChatId(String.valueOf(chatId)))
                .thenReturn(Optional.of(new TelegramUser()));

        String expectedMessage = "Пока у вас нет подписок на группы. Чтобы добавить подписку, необходимо написать /addgroupsub n," +
                "где n - номер группы, на которой вы хотите подписаться и получать свежие статьи";

        command.execute(update);

        Mockito.verify(sendBotMessageService).sendMessage(chatId.toString(), expectedMessage);
    }

    @Test
    public void return_Subscription_List_Test() {
        Long chatId = 12345L;
        Update update = prepareUpdate(chatId, DELETE_GROUP_SUB.getCommandName());
        TelegramUser telegramUser = new TelegramUser();
        GroupSub gs1 = new GroupSub();
        gs1.setId(123);
        gs1.setTitle("GS1 Title");
        telegramUser.setGroupSubs(Collections.singletonList(gs1));
        Mockito.when(telegramUserService.findByChatId(String.valueOf(chatId)))
                .thenReturn(Optional.of(telegramUser));

        String message = "Чтобы удалить подписку на группу - напишите команду вместе с ID группы. \n" +
                "Например: /deletegroupsub 16 \n\n" +
                "Для вас подготовлен список всех групп, на которые вы подписаны \n\n" +
                "имя группы - ID группы \n\n" +
                "GS1 Title";

        command.execute(update);

        Mockito.verify(sendBotMessageService).sendMessage(chatId.toString(), message);
    }*/
}
