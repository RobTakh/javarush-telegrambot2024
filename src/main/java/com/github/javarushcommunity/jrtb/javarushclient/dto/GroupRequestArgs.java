package com.github.javarushcommunity.jrtb.javarushclient.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Request arguments for group requests
 */
@Builder
@Getter
public class GroupRequestArgs {
    private final String query;
    private final GroupInfoType type;
    private final GroupFilter filter;

    /**
     * Specified where to start getting groups
     */
    private final Integer offset;

    /**
     * Limited number of groups
     */
    private final Integer limit;

    public GroupRequestArgs(
            String query,
            GroupInfoType type,
            GroupFilter filter,
            Integer offset,
            Integer limit
    ) {
        this.query = query;
        this.type = type;
        this.filter = filter;
        this.offset = offset;
        this.limit = limit;
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
        if (Objects.nonNull(offset)) {
            queries.put("offset", offset);
        }
        if (Objects.nonNull(limit)) {
            queries.put("limit", limit);
        }
        return queries;
    }

}
