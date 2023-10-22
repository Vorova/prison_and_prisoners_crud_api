package com.vorova.service;


import com.vorova.enums.ActionType;
import com.vorova.enums.ModelType;

public interface LogService {

    void addLog(ActionType type, ModelType modelType, Long subjectId, Long userId);
}
