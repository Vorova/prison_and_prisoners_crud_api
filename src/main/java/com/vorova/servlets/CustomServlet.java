package com.vorova.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Общий Servlet, необходим для привнесения доп. логики в работу всех кастомных Servlets
 */
public class CustomServlet extends HttpServlet {

    ObjectMapper mapper = new ObjectMapper();

    /**
     * Данный метод получает информацию из тела запроса
     * и возвращает текстовое представление. <br>
     * Данная логика вынесена в отдельный метод и может использоваться любым servlet, унаследованным
     * от CustomServlet
     * @param reader reader, который назначен для чтения тела запроса.
     * @return String
     */
    protected String getBody(BufferedReader reader) {
        StringBuilder content = new StringBuilder();
        try {
            String inputLine;
            BufferedReader buffer = new BufferedReader(reader);
            while ((inputLine = buffer.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException();
        }
    }
}
