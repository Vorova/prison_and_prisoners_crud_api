package com.vorova.service.impl;

import com.vorova.dao.impl.LogDao;
import com.vorova.dao.impl.LogDaoImpl;
import com.vorova.enums.ActionType;
import com.vorova.model.LogPrisonModel;
import com.vorova.service.LogService;

import java.time.LocalDateTime;

/**
 * Реализация LogService - логика логирования приложения
 */
public class LogServiceImpl implements LogService {

    private LogDao logDao = new LogDaoImpl();

    @Override
    public void addLog(ActionType type, Long prisonId, Long userId) {
        var model = new LogPrisonModel(type, prisonId, userId, LocalDateTime.now());
        logDao.addLog(model);
    }
}
