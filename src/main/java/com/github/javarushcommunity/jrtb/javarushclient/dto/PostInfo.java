package com.github.javarushcommunity.jrtb.javarushclient.dto;

import lombok.Data;

import java.util.List;

/**
 * DTO, which represents post information
 */
@Data
public class PostInfo {

    private String alternativeGroupKey;
    private List<String> alternatives;
    private AuthorInfo authorInfo;
    private Integer commentsCount;
    private String content;
    private Long createdTime;
    private String description;
    private GroupInfo groupInfo;
    private Integer id;
    private String key;
    private GroupLanguage language;
    private LikesInfo likesInfo;
    private GroupInfo originalGroupInfo;
    private String pictureUrl;
    private Double rating;
    private Integer ratingCount;
    private String title;
    private PostType type;
    private Long updatedTime;
    private UserDiscussInfo userDiscussInfo;
    private Integer views;
    private VisibilityStatus visibilityStatus;
}
