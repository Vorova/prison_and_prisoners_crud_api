package com.vorova.service;

import com.vorova.model.PrisonModel;

import java.util.List;

public interface PrisonService {

    Long create(PrisonModel prison);

    void update(Long id, PrisonModel prison);

    void delete(Long id);

    PrisonModel findById(Long id);

    List<PrisonModel> findAll();

}