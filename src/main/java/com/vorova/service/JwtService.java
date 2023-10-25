package com.vorova.service;

import com.vorova.model.dto.JwtTokenDto;

import java.util.Map;

public interface JwtService {

    JwtTokenDto generateToken(String login);

    Map<String, Object> parseToken(String token);

}
