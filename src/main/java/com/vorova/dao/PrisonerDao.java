package com.vorova.dao;

import com.vorova.model.PrisonerModel;

import java.util.List;
import java.util.Optional;

public interface PrisonerDao {


    void persist(PrisonerModel prisoner);

    void update(Long prisonerId, PrisonerModel prisoner);
    void delete(Long prisonerId);

    Optional<PrisonerModel> findById(Long prisonerId);

    List<PrisonerModel> findAllByPrisonId(long prisonId);

}
