package com.vorova.model;

import lombok.Data;

/**
 * Сущность пользователя
 */
@Data
public class UserModel {

    private Long id;
    private String login;
    private String name;
    private String password;

}
