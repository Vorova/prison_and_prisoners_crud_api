package com.vorova.service.impl;

import com.vorova.dao.UserDao;
import com.vorova.dao.impl.UserDaoImpl;
import com.vorova.model.dto.JwtTokenDto;
import com.vorova.model.entity.UserModel;
import com.vorova.service.JwtService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtServiceImpl implements JwtService {

    private static final String key = "AABBCCDDEEFFGGHHIIJJKKLLMMNNOOPPAABBCCDDEEFFGGHHIIJJKKLLMMNNOOPP";
    private final SecretKey secretKey;
    private UserDao userDao = new UserDaoImpl();

    public JwtServiceImpl() {
        secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA512");
    }

    @Override
    public JwtTokenDto generateToken(String login) {
        UserModel user = userDao.findByLogin(login).orElseThrow();
        System.out.println(user);
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("login", user.getLogin());

        JwtTokenDto jwtDto = new JwtTokenDto();

        String jws = Jwts.builder()
                .expiration(Date.from(Instant.now().plusSeconds(3600*24*30)))
                .encryptWith(secretKey, Jwts.ENC.A256CBC_HS512)
                .claims(claims)
                .compact();

        jwtDto.setJwt(jws);

        return jwtDto;
    }

    @Override
    public Map<String, Object> parseToken(String token) {
        try {
            return Jwts.parser()
                    .decryptWith(secretKey)
                    .build()
                    .parseEncryptedClaims(token).getPayload();

        } catch (JwtException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
