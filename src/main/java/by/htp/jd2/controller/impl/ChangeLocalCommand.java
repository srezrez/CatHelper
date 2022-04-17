package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLocalCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute("local");
        if(locale == null) locale = "ru";
        locale = locale == "ru" ? "en" : "ru";
        session.setAttribute("local", locale);
        String param = request.getParameter("url");
        response.sendRedirect("MyController?command=" + request.getParameter("url"));
    }
}
