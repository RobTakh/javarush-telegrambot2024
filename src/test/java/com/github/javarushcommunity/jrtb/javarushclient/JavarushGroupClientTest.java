package com.github.javarushcommunity.jrtb.javarushclient;

import com.github.javarushcommunity.jrtb.javarushclient.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Itegration tests for JavarushGroupClientImpl")
public class JavarushGroupClientTest {

    private final JavarushGroupClient groupClient = new JavarushGroupClientImpl("https://javarush.com/api/1.0/rest");

    @Test
    public void get_Groups_With_Empty_Args_Test() {
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        List<GroupInfo> groupInfoList = groupClient.getGroupList(args);
        Assertions.assertNotNull(groupInfoList);
        Assertions.assertFalse(groupInfoList.isEmpty());
    }

    @Test
    public void get_With_Offset_And_Limit_Test() {
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        List<GroupInfo> groupInfoList = groupClient.getGroupList(args);
        Assertions.assertNotNull(groupInfoList);
        Assertions.assertEquals(3, groupInfoList.size());
    }

    @Test
    public void get_Group_Disc_With_Empty_Args_Test() {
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        List<GroupDiscussionInfo> groupDiscussionInfoList = groupClient.getGroupDiscussionList(args);

        Assertions.assertNotNull(groupDiscussionInfoList);
        Assertions.assertFalse(groupDiscussionInfoList.isEmpty());
    }

    @Test
    public void get_Group_Disc_With_Offset_And_Limit_Test() {
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void get_Group_Count_Test() {
        GroupCountRequestArgs args = GroupCountRequestArgs.builder().build();

        Integer groupCount = groupClient.getGroupCount(args);

        Assertions.assertEquals(30, groupCount);
    }

    @Test
    public void get_Group_TECH_Count_Test() {
        GroupCountRequestArgs args = GroupCountRequestArgs.builder()
                .type(GroupInfoType.TECH)
                .build();

        Integer groupCount = groupClient.getGroupCount(args);

        Assertions.assertEquals(7, groupCount);
    }

    @Test
    public void get_Group_By_Id_Test() {
        Integer androidGroupId = 16;

        GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);

        Assertions.assertNotNull(groupById);
        Assertions.assertEquals(16, groupById.getId());
        Assertions.assertEquals(GroupInfoType.TECH, groupById.getType());
        Assertions.assertEquals("android", groupById.getKey());
    }
}
