package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.INFO_JSP_PATH;

public class ChangeLanguage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //прислать запрос с любой страницы с любой ситуации
        String locale = request.getParameter("locale");

        String url = (String)request.getSession().getAttribute("url");
        response.sendRedirect(url);
    }
}
