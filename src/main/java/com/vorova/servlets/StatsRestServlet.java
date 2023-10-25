package com.vorova.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorova.model.dto.StatsDto;
import com.vorova.service.StatsService;
import com.vorova.service.impl.StatsServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

/**
 * Servlet для статистики
 */
@WebServlet("/api/stats")
public class StatsRestServlet extends CustomRestServlet {

    private StatsService statsService = new StatsServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Эндпоинт возврата полной статистики по Тюрьмам и Заключенным <br>
     * Http code 200 при успешном возврате статистики<br>
     * Http code 502 если не удалось сформировать и отправить статистику<br>
     *
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     *
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            StatsDto stats = statsService.get();

            response.setStatus(200);
            response.setContentType("application/json");

            PrintWriter writer = response.getWriter();
            writer.write(mapper.writeValueAsString(stats));
        } catch (Exception e) {
            response.setStatus(502);
        }
    }
}