package com.github.javarushcommunity.jrtb.service;

import com.github.javarushcommunity.jrtb.javarushclient.JavarushGroupClient;
import com.github.javarushcommunity.jrtb.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.jrtb.repository.GroupSubRepository;
import com.github.javarushcommunity.jrtb.repository.entity.GroupSub;
import com.github.javarushcommunity.jrtb.repository.entity.TelegramUser;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupSubServiceImpl implements GroupSubService {

    private final GroupSubRepository groupSubRepository;
    private final TelegramUserService telegramUserService;
    private final JavarushGroupClient javarushGroupClient;

    public GroupSubServiceImpl(
            GroupSubRepository groupSubRepository,
            TelegramUserService telegramUserService,
            JavarushGroupClient javarushGroupClient
    ) {
        this.groupSubRepository = groupSubRepository;
        this.telegramUserService = telegramUserService;
        this.javarushGroupClient = javarushGroupClient;
    }

    @Override
    public GroupSub save(GroupSub groupSub) {
        return groupSubRepository.save(groupSub);
    }

    @Override
    public Optional<GroupSub> findById(Integer id) {
        return groupSubRepository.findById(id);
    }

    @Override
    public List<GroupSub> findAll() {
        return groupSubRepository.findAll();
    }

    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {
        TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
        // TODO add exception handling
        GroupSub groupSub;
        Optional<GroupSub> groupSubFromDb = groupSubRepository.findById(groupDiscussionInfo.getId());
        if (groupSubFromDb.isPresent()) {
            groupSub = groupSubFromDb.get();
            Optional<TelegramUser> first = groupSub.getUsers().stream()
                    .filter(db -> db.getChatId().equalsIgnoreCase(chatId))
                    .findFirst();
            if (first.isEmpty()) {
                groupSub.addUser(telegramUser);
            }
        } else {
            groupSub = new GroupSub();
            groupSub.addUser(telegramUser);
            groupSub.setLastArticleId(javarushGroupClient.findLastArticleId(groupDiscussionInfo.getId()));
            groupSub.setId(groupDiscussionInfo.getId());
            groupSub.setTitle(groupDiscussionInfo.getTitle());
        }
        return groupSubRepository.save(groupSub);
    }
}
