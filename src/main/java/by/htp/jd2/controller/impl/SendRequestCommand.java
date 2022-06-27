package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.Request;
import by.htp.jd2.entity.Status;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.RequestService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static by.htp.jd2.util.ConstantPool.*;

public class SendRequestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RequestService requestService = serviceFactory.getRequestService();
        HttpSession session = request.getSession(true);
        Request req = new Request(new Date(), null, (User)session.getAttribute(USER_ATTRIBUTE),
                new Cat(Integer.parseInt(request.getParameter("id-cat"))), Status.REQUEST_CREATED);
        try {
            requestService.sendRequest(req);
            response.sendRedirect(MAIN_PAGE_REDIRECT);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + SEND_REQUEST_ERROR_MS);
        }

    }
}
