package com.github.javarushcommunity.jrtb.javarushclient;

import com.github.javarushcommunity.jrtb.javarushclient.dto.*;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of {@link JavarushGroupClient}.
 */
@Component
public class JavarushGroupClientImpl implements JavarushGroupClient {

    private final String javarushApiGroupPath;
    private final String javarushApiPostPath;

    public JavarushGroupClientImpl(@Value("${javarush.api.path}") String javarushApi) {
        this.javarushApiGroupPath = javarushApi + "/groups";
        this.javarushApiPostPath = javarushApi + "/posts";
    }
    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupInfo>>() {
                })
                .getBody();
    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {
                })
                .getBody();
    }

    @Override
    public Integer getGroupCount(GroupCountRequestArgs countRequestArgs) {
        return Integer.valueOf(
                Unirest.get(String.format("%s/count", javarushApiGroupPath))
                .queryString(countRequestArgs.populateQueries())
                .asString()
                .getBody()
        );
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(String.format("%s/group%s", javarushApiGroupPath, id.toString()))
                .asObject(GroupDiscussionInfo.class)
                .getBody();
    }

    @Override
    public Integer findLastArticleId(Integer groupSubId) {
        List<PostInfo> posts = Unirest.get(javarushApiPostPath)
                .queryString("order", "NEW")
                .queryString("groupKid", groupSubId.toString())
                .queryString("limit", "1")
                .asObject(new GenericType<List<PostInfo>>() {
                })
                .getBody();
        return CollectionUtils.isEmpty(posts) ? 0: Optional.ofNullable(posts.get(0)).map(PostInfo::getId).orElse(0);
    }
}
