package com.vorova.service;

import com.vorova.model.dto.LoginDto;

public interface UserService {

    boolean login(LoginDto loginDto);

}
