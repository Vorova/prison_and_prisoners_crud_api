package com.vorova.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorova.enums.ActionType;
import com.vorova.enums.ModelType;
import com.vorova.model.PrisonerModel;
import com.vorova.service.LogService;
import com.vorova.service.PrisonerService;
import com.vorova.service.impl.LogServiceImpl;
import com.vorova.service.impl.PrisonerServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

/**
 * CRUD REST servlet, который работает с сущностью Prisoner
 */
@WebServlet("/api/prisoner")
public class PrisonerRestServlet extends CustomRestServlet {

    private final static PrisonerService prisonerService = new PrisonerServiceImpl();
    private final LogService logService = new LogServiceImpl();
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
            logService.addLog(ActionType.GET_PRISONER, ModelType.PRISONER, prisonerId, USER_ID());

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
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        var requestBody = getBodyFromRequest(request);
        try {
            PrisonerModel prisoner = mapper.readValue(requestBody, PrisonerModel.class);
            Long prisonerId = prisonerService.create(prisoner);
            logService.addLog(ActionType.ADD_PRISONER, ModelType.PRISONER, prisonerId, USER_ID());
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
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        var requestBody = getBodyFromRequest(request);
        try {
            PrisonerModel prisoner = mapper.readValue(requestBody, PrisonerModel.class);
            prisonerService.update(prisoner.getId(), prisoner);
            logService.addLog(ActionType.UPDATE_PRISONER, ModelType.PRISONER, prisoner.getId(), USER_ID());
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
            logService.addLog(ActionType.DELETE_PRISONER, ModelType.PRISONER, prisonerId, USER_ID());
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(400);
        }
    }


    /**
     * Возвращает из контекста id авторизованного пользователя
     * @return id авторизованного пользователя
     */
    private long USER_ID() {
        return (int) getServletContext().getAttribute("user_id");
    }
}