package com.zhenmei.wsc.demo.service;

import cn.hutool.core.thread.ThreadUtil;
import com.zhenmei.wsc.demo.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ILockWaitService implements LockWaitService {


    @Autowired
    private TestDao testDao;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Async
    public void executeSome() {
        System.out.println("execute...");

        testDao.updateSome();

        ThreadUtil.sleep(50000);
    }
}
