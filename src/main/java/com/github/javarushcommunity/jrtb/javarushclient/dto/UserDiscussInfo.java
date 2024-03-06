package com.github.javarushcommunity.jrtb.javarushclient.dto;

import lombok.Data;

/**
 * DTO of user discuss info.
 */
@Data
public class UserDiscussInfo {
    private Boolean isBookmarked;
    private Integer lastTime;
    private Integer newCommentsCount;
}
