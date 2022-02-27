package com.myblog;

import com.myblog.entity.Type;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyBlogApplicationTests {

    @Test
    void contextLoads() {
        Type type = new Type();
        System.out.println(type.getId());
    }

}
