package com.vorova.service.impl;

import com.vorova.dao.PrisonDao;
import com.vorova.dao.impl.PrisonDaoImpl;
import com.vorova.model.PrisonModel;
import com.vorova.service.PrisonService;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервисного CRUD слоя над сущностью Prison <br>
 * Данная реализация выполнена с использованием шаблона проектирования SingleTone <br>
 *
 */
public class PrisonServiceImpl implements PrisonService {

    private final PrisonDao prisonDao = PrisonDaoImpl.getInstance();

    private static final PrisonServiceImpl instance;

    private PrisonServiceImpl() {}

    static {
        instance = new PrisonServiceImpl();
    }

    public static PrisonServiceImpl getInstance(){
        return instance;
    }

    /**
     * Отправляет запрос в dao слой для сохранения сущности
     * @param prison сущность, которую необходимо сохранить
     */
    @Override
    public void create(PrisonModel prison) {
        prisonDao.persist(prison);
    }

    /**
     * Отправляет запрос в dao слой для обновления сущности
     * @param id primary key сущности, которую необходимо обновить
     * @param prison новая сущность
     */
    @Override
    public void update(Long id, PrisonModel prison) {
        prisonDao.update(id, prison);
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

    @Override
    public List<PrisonModel> findAll() {
        return prisonDao.findAll();
    }

}
