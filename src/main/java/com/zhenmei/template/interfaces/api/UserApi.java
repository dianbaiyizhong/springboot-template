package com.zhenmei.template.interfaces.api;

import com.zhenmei.template.application.service.UserService;
import com.zhenmei.template.infrastructure.response.ApiResponseDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {

        return ApiResponseDataBuilder.success(userService.getUser(id));
    }

}
