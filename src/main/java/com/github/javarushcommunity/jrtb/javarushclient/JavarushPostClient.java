package com.github.javarushcommunity.jrtb.javarushclient;

import com.github.javarushcommunity.jrtb.javarushclient.dto.PostInfo;

import java.util.List;

/**
 * Client for Javarush Open API corresponds to Posts
 */
public interface JavarushPostClient {

    /**
     * Find new posts since lastPostId in group.
     *
     * @param groupId           group ID
     * @param lastPostId        last post ID
     * @return the collection of the new {@link PostInfo}.
     */
    List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);
}
