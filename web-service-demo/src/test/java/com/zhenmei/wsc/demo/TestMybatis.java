package com.zhenmei.wsc.demo;

import com.zhenmei.wsc.demo.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatis {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void test() {

        List<Map<String,String>> list = new ArrayList<>();
        list = testMapper.getList();

        System.out.println(list.get(0).get("gmt_create"));




    }


}
