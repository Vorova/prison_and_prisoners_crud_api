package com.vorova.service;


import com.vorova.enums.ActionType;

public interface LogService {

    void addLog(Long userId, Long subjectId, ActionType type);
}
