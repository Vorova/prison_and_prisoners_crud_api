package com.vorova.dao;

import com.vorova.model.entity.PrisonModel;

import java.util.List;
import java.util.Optional;

public interface PrisonDao {

    Long persist(PrisonModel prison);

    void update(PrisonModel prison);

    void delete(Long prisonId);

    Optional<PrisonModel> findById(Long id);

    List<PrisonModel> findAll();
}
