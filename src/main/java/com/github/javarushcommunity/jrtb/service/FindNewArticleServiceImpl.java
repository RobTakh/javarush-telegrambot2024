package com.github.javarushcommunity.jrtb.service;

import com.github.javarushcommunity.jrtb.javarushclient.JavarushPostClient;
import com.github.javarushcommunity.jrtb.javarushclient.dto.PostInfo;
import com.github.javarushcommunity.jrtb.repository.entity.GroupSub;
import com.github.javarushcommunity.jrtb.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FindNewArticleServiceImpl implements FindNewArticleService {

    public static final String JAVARUSH_WEB_POST_FORMAT = "https://javarush.com/groups/posts/%s";
    private final GroupSubService groupSubService;
    private final JavarushPostClient javarushPostClient;
    private final SendBotMessageService sendBotMessageService;

    @Autowired
    public FindNewArticleServiceImpl(GroupSubService groupSubService, JavarushPostClient javarushPostClient, SendBotMessageService sendBotMessageService) {
        this.groupSubService = groupSubService;
        this.javarushPostClient = javarushPostClient;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void findNewArticles() {
        groupSubService.findAll().forEach(groupSub -> {
            List<PostInfo> newPosts = javarushPostClient.findNewPosts(groupSub.getId(), groupSub.getLastArticleId());

            setNewLastArticleId(groupSub, newPosts);

            notifySubscribersAboutNewArticles(groupSub, newPosts);
        });
    }

    private void notifySubscribersAboutNewArticles(GroupSub groupSub, List<PostInfo> newPosts) {
        Collections.reverse(newPosts);
        List<String> messagesWithNewArticles = newPosts.stream()
                .map(post -> String.format("✨Вышла новая статья <b>%s</b> в группе <b>%s</b>.✨\n\n" +
                        "<b>Описание:</b> %s\n\n" +
                        "<b>Ссылка:</b> %s\n",
                        post.getTitle(), groupSub.getTitle(), post.getDescription(), getPostUrl(post.getKey())))
                .toList();

        groupSub.getUsers().stream()
                .filter(TelegramUser::isActive)
                .forEach(it -> sendBotMessageService.sendMessage(it.getChatId(), messagesWithNewArticles));
    }

    private void setNewLastArticleId(GroupSub groupSub, List<PostInfo> newPosts) {
        newPosts.stream().mapToInt(PostInfo::getId).max()
                .ifPresent(id -> {
                    groupSub.setLastArticleId(id);
                    groupSubService.save(groupSub);
                });
    }

    private String getPostUrl(String key) {
        return String.format(JAVARUSH_WEB_POST_FORMAT, key);
    }
}
