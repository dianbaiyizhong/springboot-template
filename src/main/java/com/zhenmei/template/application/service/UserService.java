package com.zhenmei.template.application.service;

import com.zhenmei.template.interfaces.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUser(String id);


    List<UserDTO> getAllUser();
}
