package com.vorova.model;

import com.vorova.model.enums.HttpCode;
import lombok.Data;

@Data
public class ResponseEntity<T> {

    private HttpCode httpCode;
    private T entity;

    public ResponseEntity(HttpCode httpCode, T entity) {
        this.entity = entity;
        this.httpCode = httpCode;
    }

}
