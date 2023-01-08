package com.zm.wsc;

import com.zm.wsc.ds1.generate.mapper.TUserMapper;
import com.zm.wsc.ds2.generate.mapper.TAdminUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {


    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TAdminUserMapper adminUserMapper;

    @Test
    public void testDs1() throws Exception {

        userMapper.selectList(null);

        adminUserMapper.selectList(null);

    }

}
