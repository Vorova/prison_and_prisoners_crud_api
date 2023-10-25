package com.vorova.dao;

import com.vorova.model.entity.PrisonerModel;

import java.util.List;
import java.util.Optional;

public interface PrisonerDao {


    Long persist(PrisonerModel prisoner);

    void update(PrisonerModel prisoner);

    void delete(Long prisonerId);

    Optional<PrisonerModel> findById(Long prisonerId);

    List<PrisonerModel> findAllByPrisonId(long prisonId);

}
