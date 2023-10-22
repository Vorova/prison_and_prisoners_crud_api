package com.vorova.dao.impl;

import com.vorova.config.ConnectionManager;
import com.vorova.dao.LogDao;
import com.vorova.model.LogModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class LogDaoImpl implements LogDao {

    @Override
    public void addLog(LogModel logModel) {
        String INSERT_SQL = """
                    INSERT INTO logs(user_id, prison_id, action, model) VALUES (?, ?, ?, ?)
                    """;
        switch (logModel.getModel().name()) {
            case "PRISON" -> INSERT_SQL = """
                    INSERT INTO logs(user_id, prison_id, action, model) VALUES (?, ?, ?, ?)
                    """;
            case "PRISONER" -> INSERT_SQL = """
                    INSERT INTO logs(user_id, prisoner_id, action, model) VALUES (?, ?, ?, ?)
                    """;
        }

        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setLong(1, logModel.getUserId());
            statement.setLong(2, logModel.getSubjectId());
            statement.setString(3, logModel.getActionType().name());
            statement.setString(4, logModel.getModel().name());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось добавить лог");
        }
    }
}
