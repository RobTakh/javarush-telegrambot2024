package com.github.javarushcommunity.jrtb.javarushclient;

import com.github.javarushcommunity.jrtb.javarushclient.dto.PostInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Integration test for JavaRushPostClient")
public class JavarushPostClientTest {

    private final JavarushPostClient postClient = new JavarushPostClientImpl("https://javarush.com/api/1.0/rest");

    @Test
    public void get_New_15_Posts_Test() {
        List<PostInfo> newPosts = postClient.findNewPosts(30, 2935);

        Assertions.assertEquals(15, newPosts.size());
    }
}
