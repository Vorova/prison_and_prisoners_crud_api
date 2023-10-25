package com.vorova.model.dto;

import com.vorova.enums.HttpCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO ответа от сервера
 * @param <T>
 */
@Data
@AllArgsConstructor
public class ResponseDto<T> {

    private HttpCode code;
    private T response;

}
