package com.vorova.service;

import com.vorova.model.entity.PrisonerModel;

public interface PrisonerService {

    Long create(PrisonerModel prisoner);

    void update(PrisonerModel prisoner);

    void delete(Long prisonerId);

    PrisonerModel findById(Long prisonerId);

}
