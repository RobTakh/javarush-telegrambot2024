package com.github.javarushcommunity.jrtb.service;

import com.github.javarushcommunity.jrtb.dto.GroupStatDTO;
import com.github.javarushcommunity.jrtb.dto.StatisticDTO;
import com.github.javarushcommunity.jrtb.repository.entity.GroupSub;
import com.github.javarushcommunity.jrtb.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.Collections;

@DisplayName("Unit-test for StatisticsService")
public class StatisticsServiceTest {

    private GroupSubService groupSubService;
    private TelegramUserService telegramUserService;

    private StatisticService statisticService;

    @BeforeEach
    public void init() {
        groupSubService = Mockito.mock(GroupSubService.class);
        telegramUserService = Mockito.mock(TelegramUserService.class);
        statisticService = Mockito.mock(StatisticService.class);
    }

    /*@Test
    public void get_StatDTO_And_Check_It_Test() {
        Mockito.when(telegramUserService.findAllInactiveUsers()).thenReturn(Collections.singletonList(new TelegramUser()));
        TelegramUser activeUser = new TelegramUser();
        activeUser.setGroupSubs(Collections.singletonList(new GroupSub()));
        Mockito.when(telegramUserService.findAllActiveUsers()).thenReturn(Collections.singletonList(activeUser));
        GroupSub groupSub = new GroupSub();
        groupSub.setTitle("group");
        groupSub.setId(1);
        groupSub.setUsers(Collections.singletonList(new TelegramUser()));
        Mockito.when(groupSubService.findAll()).thenReturn(Collections.singletonList(groupSub));

        StatisticDTO statisticDTO = statisticService.countBotStatistic();

        Assertions.assertNotNull(statisticDTO);
        Assertions.assertEquals(1, statisticDTO.getActiveUserCount());
        Assertions.assertEquals(1, statisticDTO.getInactiveUserCount());
        Assertions.assertEquals(1.0, statisticDTO.getAverageGroupCountByUser());
        Assertions.assertEquals(Collections.singletonList(
                new GroupStatDTO(
                        groupSub.getId(),
                        groupSub.getTitle(),
                        groupSub.getUsers().size())),
                        statisticDTO.getGroupStatDTOs()
                );
    }*/
}
