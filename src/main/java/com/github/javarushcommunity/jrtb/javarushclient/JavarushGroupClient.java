package com.github.javarushcommunity.jrtb.javarushclient;

import com.github.javarushcommunity.jrtb.javarushclient.dto.GroupCountRequestArgs;
import com.github.javarushcommunity.jrtb.javarushclient.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.jrtb.javarushclient.dto.GroupInfo;
import com.github.javarushcommunity.jrtb.javarushclient.dto.GroupRequestArgs;

import java.util.List;

/**
 * Client for Javarush Open API corresponds to Groups.
 */
public interface JavarushGroupClient {

    /**
     * Get all the {@link GroupInfo} filtered by {@link GroupRequestArgs}.
     *
     * @param requestArgs       request arguments {@link GroupRequestArgs}
     * @return                  the collection of the {@link GroupInfo} objects
     */
    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);

    /**
     * Get all the {@link GroupDiscussionInfo} filtered by {@link GroupRequestArgs}.
     *
     * @param requestArgs       request arguments {@link GroupRequestArgs}
     * @return                  the collection of the {@link GroupDiscussionInfo} objects
     */
    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);

    /**
     * Get the count of groups filtered by {@link GroupCountRequestArgs}.
     *
     * @param countRequestArgs  request arguments {@link GroupCountRequestArgs}
     * @return                  the count of the groups
     */
    Integer getGroupCount(GroupCountRequestArgs countRequestArgs);

    /**
     * Get {@link GroupDiscussionInfo} by Id.
     *
     * @param id                Id
     * @return                  the {@link GroupDiscussionInfo} object
     */
    GroupDiscussionInfo getGroupById(Integer id);

    /**
     * Find ID of the newest article of subscription group
     *
     * @param groupSub
     * @return
     */
    Integer findLastArticleId(Integer groupSub);
}
