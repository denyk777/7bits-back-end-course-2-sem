package it.sevenbits.courses.servlet.servlets;

import it.sevenbits.courses.servlet.CookieWorker;
import it.sevenbits.courses.servlet.SessionRepository;
import it.sevenbits.courses.servlet.Task;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class tasks extends HttpServlet {
    private TasksRepository repository = TasksRepository.getInstance();
    private SessionRepository session = SessionRepository.getInstance();

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            response.setHeader("Authorization", CookieWorker.getCookie(request));
            if (session.getName(UUID.fromString(request.getHeader("Authorization"))) != null) {
                try {
                    for (int i = 0; i < repository.getSize(); i++) {
                        response.getWriter().write(String.format("{ \"id\":%d,\"name\":\"%S\"}\n",
                                repository.getTask(i).getID(), repository.getTask(i).getName()));
                    }
                    response.setStatus(200);
                } catch (IOException e) {
                    response.setStatus(402);
                }
            } else {
                response.setStatus(403);
            }
        } catch (NullPointerException e) {
            response.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.setHeader("Authorization", CookieWorker.getCookie(request));
            if (session.getName(UUID.fromString(request.getHeader("Authorization"))) != null) {
                Task newTask = new Task(request.getParameter("key"));
                response.getWriter().write(request.getParameter("key"));
                repository.addTask(newTask);
                response.setStatus(200);
                response.getWriter().write(String.format("{ \"id\":%d,\"name\":\"%S\"}", newTask.getID(), newTask.getName()));
            } else {
                response.setStatus(403);
            }
        } catch (IOException e) {
            response.setStatus(500);
        }
    }
}
