package com.zhenmei.wsc.demo;

import cn.hutool.core.thread.ThreadUtil;
import com.zhenmei.wsc.demo.service.ILockWaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TestLockWait {

    @Autowired
    private ILockWaitService lockWaitService;

    @Scheduled(fixedRate = 200000)
    public void execute() {

        for (int i = 0; i < 20000; i++) {
            ThreadUtil.execute(new Runnable() {
                @Override
                public void run() {
                    lockWaitService.executeSome();

                }
            });
        }


    }


}
