package com.vorova.model.enums;

public enum HttpCode {

    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    BAD_GATEWAY(502);

    HttpCode(int code) {}

}