package com.vorova.servlets;

import java.io.BufferedReader;
import java.io.IOException;

public interface MainServlet {

    default String getBody(BufferedReader reader) {
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
