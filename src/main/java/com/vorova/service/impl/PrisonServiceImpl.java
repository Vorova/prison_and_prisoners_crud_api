package com.vorova.service.impl;

import com.vorova.dao.PrisonDao;
import com.vorova.dao.impl.PrisonDaoImpl;
import com.vorova.model.entity.PrisonModel;
import com.vorova.service.PrisonService;

import java.util.Optional;


/**
 * Реализация сервисного CRUD слоя над сущностью Prison <br>
 * Данная реализация выполнена с использованием шаблона проектирования SingleTone <br>
 *
 */
public class PrisonServiceImpl implements PrisonService {

    private PrisonDao prisonDao = new PrisonDaoImpl();

    /**
     * Отправляет запрос в dao слой для сохранения сущности
     * @param prison сущность, которую необходимо сохранить
     */
    @Override
    public Long create(PrisonModel prison) {
        return prisonDao.persist(prison);
    }

    /**
     * Отправляет запрос в dao слой для обновления сущности
     * @param prison новая сущность
     */
    @Override
    public void update(PrisonModel prison) {
        prisonDao.update(prison);
    }

    /**
     * Отправляет запрос в dao слой для удаления сущности по Id
     * @param id primary key удаляемой сущности
     */
    @Override
    public void delete(Long id) {
        prisonDao.delete(id);
    }

    /**
     * Получение из dao слоя сущности по её id
     * @param id сущности для получения
     * @return PrisonModel
     */
    @Override
    public PrisonModel findById(Long id) {
        return Optional.of(prisonDao.findById(id)).get().orElseThrow();
    }

}
