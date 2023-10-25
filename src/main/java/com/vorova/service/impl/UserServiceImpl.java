package com.vorova.service.impl;

import com.vorova.dao.UserDao;
import com.vorova.dao.impl.UserDaoImpl;
import com.vorova.model.dto.LoginDto;
import com.vorova.model.entity.UserModel;
import com.vorova.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean login(LoginDto loginDto) {
        Optional<UserModel> user = userDao.findByLogin(loginDto.getLogin());
        if (user.isPresent() &&
            loginDto.getPassword().equals(user.get().getPassword())) {
            return true;
        }
        return false;
    }
}
