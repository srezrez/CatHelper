package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.USER_ATTRIBUTE;
import static by.htp.jd2.util.ConstantPool.USER_INFO_JSP_PATH;

public class GoToProfilePageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        User userInfo = userService.get(((User)session.getAttribute(USER_ATTRIBUTE)).getIdPk());
        request.setAttribute("userInfo", userInfo);
        RequestDispatcher dispatcher = request.getRequestDispatcher(USER_INFO_JSP_PATH);
        dispatcher.forward(request, response);
    }
}
