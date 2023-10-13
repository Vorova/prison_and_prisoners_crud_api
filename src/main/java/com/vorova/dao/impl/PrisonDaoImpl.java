package com.vorova.dao.impl;

import com.vorova.dao.PrisonDao;
import com.vorova.model.PrisonModel;

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
        // TODO Логика сохранения Prison
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
