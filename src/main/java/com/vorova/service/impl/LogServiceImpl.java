package com.vorova.service.impl;

import com.vorova.dao.LogDao;
import com.vorova.dao.impl.LogDaoImpl;
import com.vorova.enums.ActionType;
import com.vorova.enums.ModelType;
import com.vorova.model.LogModel;
import com.vorova.service.LogService;

import java.time.LocalDateTime;

/**
 * Реализация LogService - логика логирования приложения
 */
public class LogServiceImpl implements LogService {

    private final LogDao logDao = new LogDaoImpl();

    @Override
    public void addLog(ActionType type, ModelType modelType, Long subjectId, Long userId) {
        var model = new LogModel(type, modelType, subjectId, userId, LocalDateTime.now());
        logDao.addLog(model);
    }
}
