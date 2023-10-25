package com.vorova.model.dto;

import com.vorova.enums.HttpCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO ответа с ошибкой
 */
@Data
@AllArgsConstructor
public class ResponseExceptionDto {

    private HttpCode code;
    private String message;

}
