package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.dto.GroupStatDTO;
import com.github.javarushcommunity.jrtb.dto.StatisticDTO;
import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.github.javarushcommunity.jrtb.service.StatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static com.github.javarushcommunity.jrtb.command.StatCommand.STAT_MESSAGE;
import static com.github.javarushcommunity.jrtb.command.CommandName.STAT;
import static com.github.javarushcommunity.jrtb.command.AbstractCommandTest.prepareUpdate;

@DisplayName("Unit-test for StatCommand")
public class StatCommandTest {

    private SendBotMessageService sendBotMessageService;
    private StatisticService statisticService;
    private Command statCommand;

    @BeforeEach
    public void init() {
        sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        statisticService = Mockito.mock(StatisticService.class);
        statCommand = new StatCommand(sendBotMessageService, statisticService);
    }

    @Test
    public void send_Message_Test() {
        Long chatId = 1234567L;
        GroupStatDTO groupStatDTO = new GroupStatDTO(1, "group", 1);
        StatisticDTO statisticDTO = new StatisticDTO(1, 1, Collections.singletonList(groupStatDTO), 2.5);
        Mockito.when(statisticService.countBotStatistic()).thenReturn(statisticDTO);

        statCommand.execute(prepareUpdate(chatId, STAT.getCommandName()));

        Mockito.verify(sendBotMessageService).sendMessage(chatId.toString(), String.format(STAT_MESSAGE,
                statisticDTO.getActiveUserCount(),
                statisticDTO.getInactiveUserCount(),
                statisticDTO.getAverageGroupCountByUser(),
                String.format("%s (id = %s) - %s подписчиков", groupStatDTO.getTitle(), groupStatDTO.getId(), groupStatDTO.getActiveUserCount())));
    }
}
