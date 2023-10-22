package com.vorova.model;

import com.vorova.enums.ActionType;
import com.vorova.enums.ModelType;
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
public class LogModel {

    private ActionType actionType;
    private ModelType model;
    private Long subjectId;
    private Long userId;
    private LocalDateTime dateTime;

}
