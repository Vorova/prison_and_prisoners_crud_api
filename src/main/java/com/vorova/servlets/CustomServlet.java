package com.vorova.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorova.model.ResponseDto;
import com.vorova.model.ResponseExceptionDto;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * Общий для всех остальных servlet
 */
public class CustomServlet extends HttpServlet {

    ObjectMapper mapper = new ObjectMapper();

    /**
     *  Метод возвращает строковое значение тела запроса
     */
    protected String getBodyFromRequest(HttpServletRequest request) {
        try {
            StringBuilder result = new StringBuilder();
            Reader reader = request.getReader();
            BufferedReader buffer = new BufferedReader(reader);
            String lineResult;
            while ((lineResult = buffer.readLine()) != null) {
                result.append(lineResult);
            }
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось получить тело запроса");
        }
    }

    /**
     * Метод отвечает TODO
     * @param response
     * @param responseDto
     * @throws IOException
     */
    protected void sendResponse(HttpServletResponse response, ResponseDto<?> responseDto) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setStatus(responseDto.getCode().getCode());
        writer.print(mapper.writeValueAsString(responseDto));
    }

    /**
     * TODO
     * @param response
     * @param responseDto
     * @throws IOException
     */
    protected void sendResponse(HttpServletResponse response, ResponseExceptionDto responseDto) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setStatus(responseDto.getCode().getCode());
        writer.print(mapper.writeValueAsString(responseDto));
    }

}
