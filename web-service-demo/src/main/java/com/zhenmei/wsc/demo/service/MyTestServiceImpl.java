package com.zhenmei.wsc.demo.service;

import cn.hutool.core.thread.ThreadUtil;
import com.zhenmei.wsc.demo.dao.TestDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MyTestServiceImpl implements MyService {

    @Autowired
    private TestDao testDao;
    @Override
    @Transactional
    public void test() {

        log.info("______execute other start...");
        testDao.updateSome();
        log.info("______execute other end...");


    }
}
