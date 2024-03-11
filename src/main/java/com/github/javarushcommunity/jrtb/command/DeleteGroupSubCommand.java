package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.repository.entity.GroupSub;
import com.github.javarushcommunity.jrtb.repository.entity.TelegramUser;
import com.github.javarushcommunity.jrtb.service.GroupSubService;
import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.github.javarushcommunity.jrtb.service.TelegramUserService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import static java.lang.String.format;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.github.javarushcommunity.jrtb.command.CommandUtils.getMessage;
import static com.github.javarushcommunity.jrtb.command.CommandUtils.getChatId;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static com.github.javarushcommunity.jrtb.command.CommandName.DELETE_GROUP_SUB;

/**
 * Delete subscription group {@link Command}
 */
public class DeleteGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    private final GroupSubService groupSubService;

    public DeleteGroupSubCommand(
            SendBotMessageService sendBotMessageService,
            TelegramUserService telegramUserService,
            GroupSubService groupSubService
    ) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
        this.groupSubService = groupSubService;
    }


    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(DELETE_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update));
            return;
        }

        String groupId = getMessage(update).split(SPACE)[1];
        String chatId = getChatId(update);
        if (isNumeric(groupId)) {
            Optional<GroupSub> optionalGroupSub = groupSubService.findById(Integer.valueOf(groupId));
            if (optionalGroupSub.isPresent()) {
                GroupSub groupSub = optionalGroupSub.get();
                TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
                groupSub.getUsers().remove(telegramUser);
                groupSubService.save(groupSub);
                sendBotMessageService.sendMessage(chatId, format("Удалена подписка на группу: %s", groupSub.getTitle()));
            } else {
                sendBotMessageService.sendMessage(chatId, "Не найдена такая группа");
            }
        } else {
            sendBotMessageService.sendMessage(chatId, "неправильный формат ID группы.\n " +
                    "ID должно быть положительным целым числом");
        }
    }

    private void sendGroupIdList(String chatId) {
        String message;
        List<GroupSub> groupSubs = telegramUserService.findByChatId(chatId)
                .orElseThrow(NotFoundException::new)
                .getGroupSubs();
        if (CollectionUtils.isEmpty(groupSubs)) {
            message = "Пока у вас нет подписок на группы. Чтобы добавить подписку, необходимо написать /addgroupsub n," +
                    "где n - номер группы, на которой вы хотите подписаться и получать свежие статьи";
        } else {
            message = "Чтобы удалить подписку на группу - напишите команду вместе с ID группы. \n" +
                    "Например: /deletegroupsub 16 \n\n" +
                    "Для вас подготовлен список всех групп, на которые вы подписаны \n\n" +
                    "имя группы - ID группы \n\n" +
                    "%s";
        }
        String userGroupSubData = groupSubs.stream()
                .map(group -> format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        sendBotMessageService.sendMessage(chatId, format(message, userGroupSubData));
    }
}
