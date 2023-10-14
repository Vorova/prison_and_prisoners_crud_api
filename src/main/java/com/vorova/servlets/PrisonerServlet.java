package com.vorova.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorova.model.PrisonerModel;
import com.vorova.service.PrisonerService;
import com.vorova.service.impl.PrisonerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/prisoner")
public class PrisonerServlet extends HttpServlet implements MainServlet {

    private final static PrisonerService prisonerService = PrisonerServiceImpl.getInstance();
    private final static ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long prisonerId = Long.parseLong(request.getParameter("id"));

        try {
            PrisonerModel prisoner = prisonerService.findById(prisonerId);

            response.setStatus(200);
            response.setContentType("application/json");

            PrintWriter pw = response.getWriter();
            pw.write(mapper.writeValueAsString(prisoner));
        } catch (Exception e) {
            response.setStatus(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var requestBody = getBody(request.getReader());
        try {
            PrisonerModel prisoner = mapper.readValue(requestBody, PrisonerModel.class);
            prisonerService.create(prisoner);
            response.setStatus(201);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var requestBody = getBody(request.getReader());
        try {
            PrisonerModel prisoner = mapper.readValue(requestBody, PrisonerModel.class);
            prisonerService.update(prisoner.getId(), prisoner);
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long prisonerId = Long.parseLong(request.getParameter("id"));
        try {
            prisonerService.delete(prisonerId);
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }
}