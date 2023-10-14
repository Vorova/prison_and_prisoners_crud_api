package com.vorova.service.impl;

import com.vorova.dao.PrisonDao;
import com.vorova.dao.impl.PrisonDaoImpl;
import com.vorova.model.PrisonModel;
import com.vorova.service.PrisonService;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void create(PrisonModel prison) {
        prisonDao.persist(prison);
    }

    @Override
    public void update(Long id, PrisonModel prison) {
        prisonDao.update(id, prison);
    }

    @Override
    public void delete(Long id) {
        prisonDao.delete(id);
    }

    @Override
    public PrisonModel findById(Long id) {
        return Optional.of(prisonDao.findById(id)).get().orElseThrow();
    }

    @Override
    public List<PrisonModel> findAll() {
        return prisonDao.findAll();
    }

}
