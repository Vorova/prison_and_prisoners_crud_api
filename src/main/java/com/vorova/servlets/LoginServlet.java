package com.vorova.servlets;

import com.vorova.enums.HttpCode;
import com.vorova.model.LoginDto;
import com.vorova.model.ResponseExceptionDto;
import com.vorova.service.UserService;
import com.vorova.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/login")
public class LoginServlet extends CustomServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginDto loginDto = mapper.readValue(getBodyFromRequest(request), LoginDto.class);

        if (!userService.login(loginDto)) {
            sendResponse(response, new ResponseExceptionDto(
                            HttpCode.FORBIDDEN,
                            "Bad credentials"));
        }
        // todo авторизация
            // todo формирование JWT
            // todo установка jwt в header
            // todo отправка ответа или редирект
    }

}
