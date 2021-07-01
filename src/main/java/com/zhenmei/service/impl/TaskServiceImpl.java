package com.zhenmei.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import com.zhenmei.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    @Override
    @Async("taskExecutor")
    public void run(int index) {


        ThreadUtil.execAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("_______" + index);
            }
        });


    }
}
