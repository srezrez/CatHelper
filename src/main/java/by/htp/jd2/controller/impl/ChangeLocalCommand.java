package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ChangeLocalCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String locale = (String) session.getAttribute("local");
        if(locale == null) locale = "ru";
        locale = locale == "ru" ? "en" : "ru";
        session.setAttribute("local", locale);
        String reqUrl = request.getRequestURI().toString() + request.getQueryString().toString();
        String[] params = reqUrl.split("&");
        String commandParam = request.getParameter("url");
        StringBuilder paramsString = new StringBuilder();
        for (int i = 2; i< params.length; i++) {
            paramsString.append("&" + params[i]);
        }
        response.sendRedirect("MyController?command=" + request.getParameter("url") + paramsString);
    }
}
