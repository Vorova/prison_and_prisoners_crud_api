package com.vorova.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorova.model.PrisonModel;
import com.vorova.service.PrisonService;
import com.vorova.service.impl.PrisonServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/prison")
public class PrisonRestServlet extends MainServlet {

    private final PrisonService prisonService = PrisonServiceImpl.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long prisonId = Long.parseLong(request.getParameter("id"));

        try {
            PrisonModel prison = prisonService.findById(prisonId);

            response.setStatus(200);
            response.setContentType("application/json");

            PrintWriter pw = response.getWriter();
            pw.write(mapper.writeValueAsString(prison));
        } catch (Exception e) {
            response.setStatus(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var requestBody = getBody(request.getReader());
        try {
            PrisonModel prison = mapper.readValue(requestBody, PrisonModel.class);
            prisonService.create(prison);
            response.setStatus(201);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var requestBody = getBody(request.getReader());
        try {
            PrisonModel prison = mapper.readValue(requestBody, PrisonModel.class);
            prisonService.update(prison.getId(), prison);
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long prisonId = Long.parseLong(request.getParameter("id"));
        try {
            prisonService.delete(prisonId);
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

}
