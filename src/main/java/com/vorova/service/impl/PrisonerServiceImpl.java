package com.vorova.service.impl;

import com.vorova.dao.PrisonerDao;
import com.vorova.dao.impl.PrisonerDaoImpl;
import com.vorova.model.entity.PrisonerModel;
import com.vorova.service.PrisonerService;

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
    public Long create(PrisonerModel prisoner) {
        return prisonerDao.persist(prisoner);
    }

    /**
     * Отправляет запрос в dao слой, для обновления сущности
     * @param prisoner новая сущность
     */
    @Override
    public void update(PrisonerModel prisoner) {
        prisonerDao.update(prisoner);
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
}
