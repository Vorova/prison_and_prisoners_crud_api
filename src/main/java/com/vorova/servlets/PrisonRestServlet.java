package com.vorova.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorova.model.PrisonModel;
import com.vorova.service.PrisonService;
import com.vorova.service.impl.PrisonServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * CRUD REST servlet, работающий над сущностью Prison <br>
 * Принимает запросы на выполнение crud операции в отношении Prison
 */
@WebServlet("/api/prison")
public class PrisonRestServlet extends CustomRestServlet {

    private final PrisonService prisonService = new PrisonServiceImpl();
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Получение сущности Prison по id<br>
     * Http code 200 + строковое представление сущности в формате json, если сущность с таким id существует
     * Http code 404, если такой сущности нет
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     *
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
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

    /**
     * Создание сущности Prison <br>
     * Из тела запроса формируется сущность Prison и сохраняется в data base<br>
     *
     * Http code 201, если удалось создать сущность <br>
     * Http code 400, если сущность создать не удалось
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
            PrisonModel prison = mapper.readValue(requestBody, PrisonModel.class);
            prisonService.create(prison);
            response.setStatus(201);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

    /**
     * Обновление сущности Prison. <br>
     * Из строкового представления тела запроса, с помощью ObjectMapper формируется сущность Prison,
     * а затем по id этой сущности происходит обновление <br>
     * Http code 204, если обновление прошло успешно <br>
     * http code 400, если обновление не удалось
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
            PrisonModel prison = mapper.readValue(requestBody, PrisonModel.class);
            prisonService.update(prison.getId(), prison);
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

    /**
     * Удаление сущности по id, полученному из get параметра 'id' <br>
     * Http code 204, если удаление прошло успешно <br>
     * Http code 400, если удаление не удалось
     *
     * @param request the {@link HttpServletRequest} object that contains the request the client made of the servlet
     *
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        long prisonId = Long.parseLong(request.getParameter("id"));
        try {
            prisonService.delete(prisonId);
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }

}
