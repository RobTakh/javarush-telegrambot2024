package com.github.javarushcommunity.jrtb.repository.entity;


import jakarta.persistence.*;

import java.util.List;

/**
 * Telegram User entity
 */
@Entity
@Table(name = "tg_user")
public class TelegramUser {

    @Id
    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<GroupSub> groupSubs;

    public String getChatId() {
        return this.chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Boolean isActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<GroupSub> getGroupSubs() {
        return this.groupSubs;
    }

    public void setGroupSubs(List<GroupSub> groupSubList) {
        this.groupSubs = groupSubList;
    }
}
