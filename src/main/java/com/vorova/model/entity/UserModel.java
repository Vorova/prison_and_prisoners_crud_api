package com.vorova.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность пользователя
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    private Long id;
    private String login;
    private String name;
    private String password;

}
