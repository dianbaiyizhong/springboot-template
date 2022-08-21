package com.zhenmei.ws.template.application.service;

import com.zhenmei.ws.template.interfaces.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUser(String id);


    List<UserDTO> getAllUser();
}
