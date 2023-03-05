package com.zhenmei.wsc.demo;

import com.zhenmei.wsc.demo.service.LockWaitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TestLockWait {

    @Autowired
    private LockWaitServiceImpl lockWaitService;

    @Scheduled(fixedRate = 200000)
    public void execute() {
        for (int i = 0; i < 200; i++) {
            lockWaitService.executeSome();
        }
    }


}
