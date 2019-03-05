package it.sevenbits.courses.servlet.servlets;

import it.sevenbits.courses.servlet.SessionRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class SessionsCreator extends HttpServlet {
    private SessionRepository session = SessionRepository.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UUID ID = UUID.fromString(request.getCookies()[0].getValue());
        String userName = session.getName(ID);
        response.getWriter().write(String.format("Current User is %S\n", userName));
        Map<UUID, String> list = session.getList();
        for (Map.Entry<UUID, String> entry : list.entrySet()) {
            response.getWriter().write(String.format("UUID = %S, Name = %S\n",entry.getKey().toString(), entry.getValue()));
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        session.addSessoin(request.getParameter("name"));
        Cookie cookie = new Cookie("SessionID", session.getID().toString());
        response.addCookie(cookie);
    }
}
