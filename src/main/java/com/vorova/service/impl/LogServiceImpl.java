package com.vorova.service.impl;

import com.vorova.dao.LogDao;
import com.vorova.dao.impl.LogDaoImpl;
import com.vorova.enums.ActionType;
import com.vorova.model.entity.LogModel;
import com.vorova.service.LogService;

import java.time.LocalDateTime;

/**
 * Реализация LogService - логика логирования приложения
 */
public class LogServiceImpl implements LogService {

    private final LogDao logDao = new LogDaoImpl();

    @Override
    public void addLog(Long userId, Long subjectId, ActionType action) {
        var log = LogModel.builder()
                .userId(userId)
                .subjectId(subjectId)
                .actionType(action)
                .dateTime(LocalDateTime.now())
                .build();
        logDao.addLog(log);
    }
}
