package com.vorova.dao.impl;

import com.vorova.config.ConnectionManager;
import com.vorova.model.LogPrisonModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class LogDaoImpl implements LogDao {

    @Override
    public void addLog(LogPrisonModel logPrisonModel) {
        String INSERT_SQL = """
            INSERT INTO logs_prison(user_id, prison_id, action) VALUES (?, ?, ?)
            """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setLong(1, logPrisonModel.getUserId());
            statement.setLong(2, logPrisonModel.getPrisonId());
            statement.setString(3, logPrisonModel.getActionType().name());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось добавить лог");
        }
    }
}
