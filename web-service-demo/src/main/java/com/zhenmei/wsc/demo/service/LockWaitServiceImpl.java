package com.zhenmei.wsc.demo.service;

import cn.hutool.core.thread.ThreadUtil;
import com.zhenmei.wsc.demo.dao.TestDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class LockWaitServiceImpl implements LockWaitService {


    @Autowired
    private TestDao testDao;


    @Autowired
    private MyService myService;

    @Override
    @Transactional
    @Async
    public void executeSome() {
        log.info("execute...start...");

        testDao.updateSome();
        myService.test();


        ThreadUtil.sleep(4000);

        log.info("execute...end...");


    }
}
