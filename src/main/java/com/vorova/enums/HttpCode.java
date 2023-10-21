package com.vorova.enums;

public enum HttpCode {

    OK(200),
    BAD_REQUEST(400);

    private final int code;

    HttpCode(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
