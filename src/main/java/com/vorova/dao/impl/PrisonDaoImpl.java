package com.vorova.dao.impl;

import com.vorova.config.ConnectionManager;
import com.vorova.dao.PrisonDao;
import com.vorova.model.PrisonModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PrisonDaoImpl implements PrisonDao {

    private static final PrisonDaoImpl instance;

    private PrisonDaoImpl() {}

    static {
        instance = new PrisonDaoImpl();
    }

    public static PrisonDaoImpl getInstance(){
        return instance;
    }

    @Override
    public void persist(PrisonModel prison) {
        String INSERT_SQL = """
            INSERT INTO prison(title) VALUES (?);
            """;
        try (Connection connection = ConnectionManager.open();
                PreparedStatement statement = connection.prepareStatement(INSERT_SQL)){
            statement.setString(1, prison.getTitle());
            statement.execute();
        } catch (SQLException e) {
            System.err.println("Не удалось сохранить Prison");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long id, PrisonModel prison) {
        // TODO Логика обновления Prison
    }

    @Override
    public void delete(Long id) {
        // TODO Логика удаления Prison
    }

    @Override
    public Optional<PrisonModel> findById(Long id) {
        // TODO Логика получения Prison по ID
        return Optional.empty();
    }

    @Override
    public List<PrisonModel> findAll() {
        // TODO Логика получения всех Prison
        return null;
    }
}
