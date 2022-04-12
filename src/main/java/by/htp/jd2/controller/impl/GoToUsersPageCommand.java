package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.htp.jd2.util.ConstantPool.ADD_CAT_JSP;
import static by.htp.jd2.util.ConstantPool.USER_LIST_JSP_PATH;

public class GoToUsersPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try {
            List<User> userList = userService.getAll();
            request.setAttribute("userList", userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher(USER_LIST_JSP_PATH);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
