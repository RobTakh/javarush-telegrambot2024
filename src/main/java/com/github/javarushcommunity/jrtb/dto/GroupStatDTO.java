package com.github.javarushcommunity.jrtb.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO, which shows group ID and title without data.
 */
@Data
@EqualsAndHashCode(exclude = {"title", "activeUserCount"})
public class GroupStatDTO {

    private final Integer id;
    private final String title;
    private final Integer activeUserCount;
}
