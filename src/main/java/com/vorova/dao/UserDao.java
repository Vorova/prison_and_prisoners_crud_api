package com.vorova.dao;

import com.vorova.model.UserModel;

import java.util.Optional;

public interface UserDao {

    Optional<UserModel> findByLogin(String login);

}
