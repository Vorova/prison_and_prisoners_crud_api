package com.vorova.service;

import com.vorova.model.PrisonerModel;

import java.util.List;

public interface PrisonerService {

    void create(PrisonerModel prisoner);

    void update(Long prisonerId, PrisonerModel prisoner);

    void delete(Long prisonerId);

    PrisonerModel findById(Long prisonerId);

    List<PrisonerModel> findAll();

}
