package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.*;

public class ChangePasswordCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try {
            userService.changePassword(((User)session.getAttribute("idUser")).getIdPk(), request.getParameter(PASSWORD_PARAMETER));
            response.sendRedirect(MAIN_PAGE_REDIRECT);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + BASIC_ERROR_MS);
        }
    }
}
