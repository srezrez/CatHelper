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
        session.setAttribute("local", request.getParameter("local"));
        response.sendRedirect("MyController?command=" + request.getParameter("url"));
    }
}
