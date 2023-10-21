package com.vorova.model;

import lombok.Data;

/**
 * DTO авторизации
 */
@Data
public class LoginDto {

    private String login;
    private String password;

}
