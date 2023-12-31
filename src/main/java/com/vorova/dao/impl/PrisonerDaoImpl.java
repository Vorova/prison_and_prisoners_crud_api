package com.vorova.dao.impl;

import com.vorova.config.ConnectionManager;
import com.vorova.dao.PrisonerDao;
import com.vorova.model.PrisonerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация dao слоя для сущности PrisonerModel. <br>
 * Выполняет преимущественно CRUD операции <br>
 * Данная реализация выполнена с использованием шаблона проектирования Single tone
 */
public class PrisonerDaoImpl implements PrisonerDao {

    /**
     * Сохранение сущности в базе
     * @param prisoner сущность, которую необходимо сохранить
     */
    @Override
    public Long persist(PrisonerModel prisoner) {
        String insert_sql = """
            INSERT INTO prisoner(name, prison_id) VALUES (?, ?);
            """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(insert_sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, prisoner.getName());
            statement.setLong(2, prisoner.getPrisonId());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getLong(1);
        } catch (SQLException e) {
            System.err.println("Не удалось сохранить Prisoner");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Обновление сущности в базе данных
     * @param prisonerId primary key, по которому необходимо обновить
     * @param prisoner новая сущность
     */
    @Override
    public void update(Long prisonerId, PrisonerModel prisoner) {
        String update_sql = """
                UPDATE prisoner SET name = ?, prison_id = ? WHERE id = ?;
                """;
        try (Connection connection = ConnectionManager.open();
             PreparedStatement statement = connection.prepareStatement(update_sql)) {
            statement.setString(1, prisoner.getName());
            statement.setLong(2, prisonerId);
            statement.setLong(3, prisoner.getPrisonId());
            if (statement.executeUpdate() < 1)
                throw new SQLException("Обновление prisoner не произошло");
        } catch (SQLException e) {
            System.err.println("Не удалось обновить Prisoner");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление сущности по id
     * @param prisonerId primary key, по которому будет происходить удаление
     */
    @Override
    public void delete(Long prisonerId) {
        String delete_sql = """
            UPDATE prisoner SET is_deleted = TRUE WHERE id = ?;
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

    /**
     * Получение сущности по primary key
     * @param prisonerId primary key
     * @return Optional for PrisonerModel
     */
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
                prisoner.setPrisonId(result.getLong("prison_id"));
                return Optional.of(prisoner);
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.err.println("Не удалось получить Prisoner");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение всех prisoners по id тюрьмы в которой они находятся
     * @param prisonId id prison
     * @return List
     */
    @Override
    public List<PrisonerModel> findAllByPrisonId(long prisonId) {
        var prisoners = new ArrayList<PrisonerModel>();

        final String SELECT_SQL = """
                SELECT * FROM prisoner WHERE prison_id = ? AND is_deleted = FALSE;
                """;
        try(Connection connection = ConnectionManager.open();
            PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
            statement.setLong(1, prisonId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                var prisoner = new PrisonerModel();
                prisoner.setId(result.getLong("id"));
                prisoner.setName(result.getString("name"));
                prisoner.setPrisonId(prisonId);
                prisoners.add(prisoner);
            }
            return prisoners;
        } catch (SQLException e) {
            System.out.println("Не удалось получить всеx prisoners");
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }
}
