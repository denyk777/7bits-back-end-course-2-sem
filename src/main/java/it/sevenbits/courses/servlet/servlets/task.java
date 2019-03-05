package it.sevenbits.courses.servlet.servlets;

import it.sevenbits.courses.servlet.CookieWorker;
import it.sevenbits.courses.servlet.SessionRepository;
import it.sevenbits.courses.servlet.servlets.TasksRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class task extends HttpServlet {
    private TasksRepository repository = TasksRepository.getInstance();
    private SessionRepository session = SessionRepository.getInstance();

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setHeader("Authorization", CookieWorker.getCookie(request));
            if (session.getName(UUID.fromString(request.getHeader("Authorization"))) != null) {
                int requestID = Integer.parseInt(request.getParameter("taskId"));
                try {
                    String name = repository.getTask(requestID).getName();
                    response.getWriter().write(String.format("{ \"id\":%d,\"name\":\"%S\"}\n", requestID, name));
                    response.setStatus(200);
                } catch (ArrayIndexOutOfBoundsException e) {
                    response.setStatus(404);
                }
            }
        } catch (NumberFormatException e) {
            response.setStatus(500);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setHeader("Authorization", CookieWorker.getCookie(request));
            if (session.getName(UUID.fromString(request.getHeader("Authorization"))) != null) {
                int requestID = Integer.parseInt(request.getParameter("taskId"));
                try {
                    repository.deleteByID(requestID);
                    response.getWriter().write(String.format("{ \"id\":%d}\n", requestID));
                    response.setStatus(200);
                } catch (ArrayIndexOutOfBoundsException e) {
                    response.setStatus(409);
                }
            } else {
                response.setStatus(404);
            }
        } catch (NumberFormatException e) {
            response.setStatus(500);
        }
    }
}
