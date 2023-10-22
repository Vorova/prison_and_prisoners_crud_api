package com.vorova.dao.impl;

import com.vorova.config.ConnectionManager;
import com.vorova.dao.PrisonDao;
import com.vorova.dao.PrisonerDao;
import com.vorova.model.PrisonModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация dao слоя для сущности PrisonModel. <br>
 * Выполняет преимущественно CRUD операции <br>
 */
public class PrisonDaoImpl implements PrisonDao {

    private final PrisonerDao prisonerDao = new PrisonerDaoImpl();

    /**
     * Сохранение сущности в базе данных
     * @param prison сохраняемая сущность
     * @param user_id id создавшего пользователя
     */
    @Override
    public void persist(PrisonModel prison, Long user_id) {
        String insert_sql = """
            INSERT INTO prison(title, user_id) VALUES (?, ?);
            """;
        try (Connection connection = ConnectionManager.open();
                PreparedStatement statement = connection.prepareStatement(insert_sql)){
            statement.setString(1, prison.getTitle());
            statement.setLong(2, user_id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println("Не удалось сохранить Prison");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Обновление сущности в базе данных
     * @param prisonId primary key для обновления
     * @param prison новая сущность
     */
    @Override
    public void update(Long prisonId, PrisonModel prison) {
        String update_sql = """
                UPDATE prison SET title = ? WHERE id = ?;
                """;
        try (Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(update_sql)) {
            statement.setString(1, prison.getTitle());
            statement.setLong(2, prisonId);
            if (statement.executeUpdate() < 1)
                throw new SQLException("Обновление не удалось");
        } catch (SQLException e) {
            System.err.println("Не удалось обновить Prison");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление сущности из базы данных
     * @param prisonId primary key по которому будет удаление
     */
    @Override
    public void delete(Long prisonId) {
        String delete_sql = """
            DELETE FROM prison WHERE id = ?;
            """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(delete_sql)){
            statement.setLong(1, prisonId);
            if (statement.executeUpdate() < 1)
                throw new RuntimeException("Ничего не было удалено!");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить Prison");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение сущности по ее primary key
     * @param prisonId primary key
     * @return Optional PrisonModel
     */
    @Override
    public Optional<PrisonModel> findById(Long prisonId) {
        String select_sql = "SELECT * FROM prison WHERE id = ?";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(select_sql)){
            statement.setLong(1, prisonId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                var prison = new PrisonModel();
                prison.setId(result.getLong("id"));
                prison.setTitle(result.getString("title"));
                prison.setUserId(result.getLong("user_id"));
                return Optional.of(prison);
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.err.println("Не удалось получить Prison");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение всех PrisonModel
     * @return List
     */
    @Override
    public List<PrisonModel> findAll() {
        var prisons = new ArrayList<PrisonModel>();

        final String SELECT_SQL = """
                SELECT * FROM prison;
                """;
        try(Connection connection = ConnectionManager.open();
            Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_SQL);
            while (result.next()) {
               var prison = new PrisonModel();
               prison.setId(result.getLong("id"));
               prison.setTitle(result.getString("title"));

               prison.setPrisoners(prisonerDao.findAllByPrisonId(prison.getId()));

               prisons.add(prison);
            }
            return prisons;
        } catch (SQLException e) {
            System.out.println("Не удалось получить все prisons");
            throw new RuntimeException();
        }
    }
}