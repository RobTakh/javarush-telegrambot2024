package com.github.javarushcommunity.jrtb.javarushclient.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Request arguments for group count requests
 */
@Builder
@Getter
public class GroupCountRequestArgs {
    private final String query;
    private final GroupInfoType type;
    private final GroupFilter filter;

    public GroupCountRequestArgs(
            String query,
            GroupInfoType type,
            GroupFilter filter
    ) {
        this.query = query;
        this.type = type;
        this.filter = filter;
    }

    public Map populateQueries() {
        Map queries = new HashMap<>();
        if (Objects.nonNull(query)) {
            queries.put("query", query);
        }
        if (Objects.nonNull(type)) {
            queries.put("type", type);
        }
        if (Objects.nonNull(filter)) {
            queries.put("filter", filter);
        }
        return queries;
    }
}
