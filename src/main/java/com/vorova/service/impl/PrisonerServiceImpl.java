package com.vorova.service.impl;

import com.vorova.dao.PrisonerDao;
import com.vorova.dao.impl.PrisonerDaoImpl;
import com.vorova.model.PrisonerModel;
import com.vorova.service.PrisonerService;

import java.util.List;
import java.util.Optional;

public class PrisonerServiceImpl implements PrisonerService {

    private static final PrisonerServiceImpl instance;
    private static final PrisonerDao prisonerDao = PrisonerDaoImpl.getInstance();

    private PrisonerServiceImpl() {}

    static {
        instance = new PrisonerServiceImpl();
    }

    public static PrisonerServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void create(PrisonerModel prisoner) {
        prisonerDao.persist(prisoner);
    }

    @Override
    public void update(Long prisonerId, PrisonerModel prisoner) {
        prisonerDao.update(prisonerId, prisoner);
    }

    @Override
    public void delete(Long prisonerId) {
        prisonerDao.delete(prisonerId);
    }

    @Override
    public PrisonerModel findById(Long prisonerId) {
        return Optional.of(prisonerDao.findById(prisonerId)).get().orElseThrow();
    }

    @Override
    public List<PrisonerModel> findAll() {
        return null;
    }
}
