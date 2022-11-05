package com.zm.demo;

import com.zm.demo.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApp {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void testMybatis() throws Exception {

        List<Map<String, String>> list = testMapper.getList();
        System.out.println(list);

    }
}
