package com.vorova.service;

import com.vorova.model.PrisonerModel;

public interface PrisonerService {

    Long create(PrisonerModel prisoner);

    void update(Long prisonerId, PrisonerModel prisoner);

    void delete(Long prisonerId);

    PrisonerModel findById(Long prisonerId);

}
