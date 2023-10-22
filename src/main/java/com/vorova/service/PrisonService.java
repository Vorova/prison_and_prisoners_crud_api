package com.vorova.service;

import com.vorova.model.PrisonModel;

import java.util.List;

public interface PrisonService {

    void create(PrisonModel prison, Long user_id);

    void update(Long id, PrisonModel prison);

    void delete(Long id);

    PrisonModel findById(Long id);

    List<PrisonModel> findAll();

}