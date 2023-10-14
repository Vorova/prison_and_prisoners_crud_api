package com.vorova.servlets;

import jakarta.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.IOException;

public class MainServlet extends HttpServlet {

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
            // TODO обработка исключений
            System.out.println(exception.getMessage());
            throw new RuntimeException();
        }
    }
}
