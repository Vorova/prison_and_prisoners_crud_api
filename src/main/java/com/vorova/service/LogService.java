package com.vorova.service;


import com.vorova.enums.ActionType;

public interface LogService {

    void addLog(ActionType type, Long prison_id, Long user_id);
}
