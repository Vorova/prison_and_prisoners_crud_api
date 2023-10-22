package com.vorova.model;

import com.vorova.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Представление логов, таблица 'logs'
 */
@Data
@AllArgsConstructor()
@NoArgsConstructor
public class LogPrisonModel {

    private ActionType actionType;
    private Long prisonId;
    private Long userId;
    private LocalDateTime dateTime;

}
