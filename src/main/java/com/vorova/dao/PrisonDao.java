package com.vorova.dao;

import com.vorova.model.PrisonModel;

import java.util.List;
import java.util.Optional;

public interface PrisonDao {

    void persist(PrisonModel prison, Long user_id);

    void update(Long id, PrisonModel prison);

    void delete(Long prisonId);

    Optional<PrisonModel> findById(Long id);

    List<PrisonModel> findAll();
}
