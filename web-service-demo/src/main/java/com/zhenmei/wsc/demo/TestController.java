package com.zhenmei.wsc.demo;

import com.zhenmei.wsc.demo.bean.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestController {
    @PostMapping("/addUser")
    public Object addUser(@Valid @RequestBody User user){
        return "用户添加成功";
    }
}
