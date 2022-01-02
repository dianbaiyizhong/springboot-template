package com.zhenmei.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import com.zhenmei.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
//    @Async("taskExecutor")
    public void run(int index) {

        String url = "http://" + "localhost" + ":" + "2408" + "/deviceInfo/unsuccess2";

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, "sss");
            System.out.println(responseEntity);
        }catch (Exception e){

            System.out.println("_______");
        }

//        ThreadUtil.execAsync(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(15000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("_______" + index);
//            }
//        });


    }
}
