package com.github.javarushcommunity.jrtb.javarushclient.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * GroupInfo class DTO.
 */
@Data
@ToString
@Getter
@Setter
public class GroupInfo {
    private String avatarUrl;
    private String createTime;
    private String description;
    private Integer id;
    private String key;
    private GroupLanguage language;
    private Integer levelToEditor;
    private MeGroupInfo meGroupInfo;
    private String pictureUrl;
    private String title;
    private GroupInfoType type;
    private Integer usersCount;
    private GroupVisibilityStatus visibilityStatus;
}
