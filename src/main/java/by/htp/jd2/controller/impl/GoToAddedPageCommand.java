package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.ADDED_JSP_PATH;
import static by.htp.jd2.util.ConstantPool.SIGN_IN_JSP_PATH;

public class GoToAddedPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(ADDED_JSP_PATH);
        dispatcher.forward(request, response);
    }
}
