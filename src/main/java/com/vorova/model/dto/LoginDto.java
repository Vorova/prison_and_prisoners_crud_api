package com.vorova.model.dto;

import lombok.Data;

/**
 * DTO авторизации
 */
@Data
public class LoginDto {

    private String login;
    private String password;

}
