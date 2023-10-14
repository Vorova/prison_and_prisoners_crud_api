package com.vorova.service.impl;

import com.vorova.dao.PrisonerDao;
import com.vorova.dao.impl.PrisonerDaoImpl;
import com.vorova.model.PrisonerModel;
import com.vorova.service.PrisonerService;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервисного CRUD слоя, над сущностью Prisoner <br>
 * Данная реализация выполнена с использованием шаблона проектирования SingleTone <br>
 * В основном выполняются CRUD операции.
 */
public class PrisonerServiceImpl implements PrisonerService {

    private PrisonerDao prisonerDao = new PrisonerDaoImpl();

    /**
     * Данный метод отправляет запрос в dao слой, для сохранения в bd
     * @param prisoner сущность, которую необходимо сохранить
     */
    @Override
    public void create(PrisonerModel prisoner) {
        prisonerDao.persist(prisoner);
    }

    /**
     * Отправляет запрос в dao слой, для обновления сущности по id
     * @param prisonerId primary key, по которому необходимо произвести обновление
     * @param prisoner новая сущность
     */
    @Override
    public void update(Long prisonerId, PrisonerModel prisoner) {
        prisonerDao.update(prisonerId, prisoner);
    }

    /**
     * Отправляет запрос в dao слой, для удаления сущности по её id
     * @param prisonerId primary key сущности, которую необходимо удалить
     */
    @Override
    public void delete(Long prisonerId) {
        prisonerDao.delete(prisonerId);
    }

    /**
     * Получает из dao слоя сущность по id
     * @param prisonerId primary key сущности, которую необходимо получить
     * @return PrisonerModel
     */
    @Override
    public PrisonerModel findById(Long prisonerId) {
        return Optional.of(prisonerDao.findById(prisonerId)).get().orElseThrow();
    }

    @Override
    public List<PrisonerModel> findAll() {
        return null;
    }
}
