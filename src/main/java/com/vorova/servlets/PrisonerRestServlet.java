package com.vorova.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorova.model.PrisonerModel;
import com.vorova.service.PrisonerService;
import com.vorova.service.impl.PrisonerServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * CRUD REST servlet, который работает с сущностью Prisoner
 */
@WebServlet("/api/prisoner")
public class PrisonerRestServlet extends CustomServlet {

    private final static PrisonerService prisonerService = new PrisonerServiceImpl();
    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     * Возвращает Prisoner по get параметру id <br>
     * Устанавливает код ответа 200, если такой Prisoner существует, и возвращает сущность Prisoner <br>
     * Устанавливает код ответа 404, если такой Prisoner не существует.
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     *
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
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

    /**
     * Полученное тело запроса формируется в сущность Prisoner <br>
     * Http code - 201, при успешном сохранении <br>
     * Http code - 400, если не удалось сохранить сущность, например,
     * не корректное тело запроса или не удачное подключение к бд
     *
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     *
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws IOException ошибка сериализации
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var requestBody = getBodyFromRequest(request);
        try {
            PrisonerModel prisoner = mapper.readValue(requestBody, PrisonerModel.class);
            prisonerService.create(prisoner);
            response.setStatus(201);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

    /**
     * Обновляет полученную сущность Prisoner <br>
     * Http code - 204, при успешном обновлении <br>
     * Http code - 400, если обновление не удалось
     *
     * @param request the {@link HttpServletRequest} object that contains the request the client made of the servlet
     *
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     *
     * @throws IOException ошибка сериализации
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var requestBody = getBodyFromRequest(request);
        try {
            PrisonerModel prisoner = mapper.readValue(requestBody, PrisonerModel.class);
            prisonerService.update(prisoner.getId(), prisoner);
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

    /**
     * Удаляет сущность, по id, полученном из get параметра <br>
     * Http code 204, при успешном удалении <br>
     * Http code 400, если удаление не удалось
     * @param request the {@link HttpServletRequest} object that contains the request the client made of the servlet
     *
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        long prisonerId = Long.parseLong(request.getParameter("id"));
        try {
            prisonerService.delete(prisonerId);
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }
}