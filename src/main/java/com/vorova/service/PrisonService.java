package com.vorova.service;

import com.vorova.model.entity.PrisonModel;

import java.util.List;

public interface PrisonService {

    Long create(PrisonModel prison);

    void update(PrisonModel prison);

    void delete(Long id);

    PrisonModel findById(Long id);

}