package com.github.javarushcommunity.jrtb.javarushclient.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * GroupDiscussionInfo DTO.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupDiscussionInfo extends GroupInfo {
    private GroupDiscussionInfo groupDiscussionInfo;
    private Integer commentsCount;
}
