package com.vorova.dao.impl;

import com.vorova.config.ConnectionManager;
import com.vorova.dao.UserDao;
import com.vorova.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public Optional<UserModel> findByLogin(String login) {

        final String SELECT_SQL = """
                SELECT * FROM users WHERE login = ?
                """;

        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                var userModel = new UserModel();
                userModel.setLogin(resultSet.getString("login"));
                userModel.setName(resultSet.getString("name"));
                userModel.setPassword(resultSet.getString("password"));
                return Optional.of(userModel);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось выполнить запрос");
        }
    }

}
