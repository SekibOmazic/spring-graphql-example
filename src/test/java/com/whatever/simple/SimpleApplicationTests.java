package com.whatever.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// TODO: using RANDOM_PORT is a workaround for a weird bug in
// com.graphql-java-kickstart:graphql-spring-boot-starter:5.3.1

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleApplicationTests {

    @Test
    public void contextLoads() {
    }

}
