package com.vorova.dao.impl;

import com.vorova.config.ConnectionManager;
import com.vorova.dao.PrisonerDao;
import com.vorova.model.PrisonerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PrisonerDaoImpl implements PrisonerDao {

    private static final PrisonerDaoImpl instance;

    private PrisonerDaoImpl() {}

    static {
        instance = new PrisonerDaoImpl();
    }

    public static PrisonerDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void persist(PrisonerModel prisoner) {
        String insert_sql = """
            INSERT INTO prisoner(name, prison_id) VALUES (?, ?);
            """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(insert_sql)){
            statement.setString(1, prisoner.getName());
            statement.setLong(2, prisoner.getPrison_id());
            if (statement.executeUpdate() < 1)
                throw new SQLException("Сохранение prisoner не произошло");
        } catch (SQLException e) {
            System.err.println("Не удалось сохранить Prisoner");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long prisonerId, PrisonerModel prisoner) {
        String update_sql = """
                UPDATE prisoner SET name = ? WHERE id = ?;
                """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(update_sql)) {
            statement.setString(1, prisoner.getName());
            statement.setLong(2, prisonerId);
            if (statement.executeUpdate() < 1)
                throw new SQLException("Обновление prisoner не произошло");
        } catch (SQLException e) {
            System.err.println("Не удалось обновить Prisoner");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long prisonerId) {
        String delete_sql = """
            DELETE FROM prisoner WHERE id = ?;
            """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(delete_sql)){
            statement.setLong(1, prisonerId);
            if (statement.executeUpdate() < 1)
                throw new RuntimeException("Prisoner не был удален!");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить Prisoner");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<PrisonerModel> findById(Long prisonerId) {
        String select_sql = "SELECT * FROM prisoner WHERE id = ?";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(select_sql)){
            statement.setLong(1, prisonerId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                var prisoner = new PrisonerModel();
                prisoner.setId(result.getLong("id"));
                prisoner.setName(result.getString("name"));
                prisoner.setPrison_id(result.getLong("prison_id"));
                return Optional.of(prisoner);
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.err.println("Не удалось получить Prisoner");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PrisonerModel> findAll() {
        return null;
    }
}