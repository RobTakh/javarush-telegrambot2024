package com.github.javarushcommunity.jrtb.service;

import com.github.javarushcommunity.jrtb.javarushclient.JavarushGroupClient;
import com.github.javarushcommunity.jrtb.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.jrtb.repository.GroupSubRepository;
import com.github.javarushcommunity.jrtb.repository.entity.GroupSub;
import com.github.javarushcommunity.jrtb.repository.entity.TelegramUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

@DisplayName("Unit-level test for GroupSubService")
public class GroupSubServiceTest {

    private GroupSubService groupSubService;
    private GroupSubRepository groupSubRepository;
    private TelegramUser newUser;
    private JavarushGroupClient javarushGroupClient;

    private final static String CHAT_ID = "1";

    @BeforeEach
    public void init() {
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        groupSubRepository = Mockito.mock(GroupSubRepository.class);
        groupSubService = new GroupSubServiceImpl(groupSubRepository, telegramUserService, javarushGroupClient);

        newUser = new TelegramUser();
        newUser.setActive(true);
        newUser.setChatId(CHAT_ID);

        Mockito.when(telegramUserService.findByChatId(CHAT_ID)).thenReturn(Optional.of(newUser));
    }

    @Test
    public void save_Group_Test() {
        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(newUser);

        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }

    /*@Test
    public void add_User_To_Existing_Group_Test() {
        TelegramUser oldTelegramUser = new TelegramUser();
        oldTelegramUser.setChatId("2");
        oldTelegramUser.setActive(true);

        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");

        GroupSub groupFromDb = new GroupSub();
        groupFromDb.setId(groupDiscussionInfo.getId());
        groupFromDb.setTitle(groupFromDb.getTitle());
        groupFromDb.addUser(oldTelegramUser);

        Mockito.when(groupSubRepository.findById(groupDiscussionInfo.getId())).thenReturn(Optional.of(groupFromDb));

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(oldTelegramUser);
        expectedGroupSub.addUser(newUser);

        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        Mockito.verify(groupSubRepository).findById(expectedGroupSub.getId());
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }*/
}
