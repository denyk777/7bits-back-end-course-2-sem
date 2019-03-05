package it.sevenbits.courses.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieWorker {
    static public String getCookie(HttpServletRequest request) {
        String ID = null;
        Cookie[] cookie = request.getCookies();
        for (Cookie cookie1 : cookie) {
            if (cookie1.getName().equals("SessionID")) {
                ID = cookie1.getValue();
            }
        }
        return ID;
    }
}
