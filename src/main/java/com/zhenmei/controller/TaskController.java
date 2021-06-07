package com.zhenmei.controller;

import com.zhenmei.constant.RestCode;
import com.zhenmei.exception.BusinessException;
import com.zhenmei.pojo.param.DeviceInfoGetParam;
import com.zhenmei.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("task")
@ResponseBody
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping("/run/{num}")
    public void run(@PathVariable int num) {

        for (int i = 0; i < num; i++) {
            taskService.run(i);
        }


        throw BusinessException.builder()
                .message("任务投递成功")
                .clientTip("任务投递成功")
                .restCode(RestCode.SUCCESS)
                .build();

    }
}
