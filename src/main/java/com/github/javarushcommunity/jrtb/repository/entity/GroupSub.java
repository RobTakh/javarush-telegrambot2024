package com.github.javarushcommunity.jrtb.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "group_sub")
@EqualsAndHashCode
public class GroupSub {

    @Id
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "last_article_id")
    private Integer lastArticleId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_x_user",
            joinColumns = @JoinColumn(name = "group_sub_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<TelegramUser> users;

    public void addUser(TelegramUser telegramUser) {
        if (Objects.isNull(users)) {
            users = new ArrayList<>();
        }
        users.add(telegramUser);
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLastArticleId() {
        return lastArticleId;
    }
}
