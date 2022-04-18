package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.RequestViewModel;
import by.htp.jd2.entity.Status;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.RequestService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.impl.RequestServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.htp.jd2.util.ConstantPool.*;

public class GoToRequestsPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RequestService requestService = serviceFactory.getRequestService();
        try {
            List<RequestViewModel> requests = requestService.getRequests(((User)session.getAttribute(USER_ATTRIBUTE)).getIdPk(), Status.REQUEST_CREATED.getIdPk());
            request.setAttribute("requests", requests);
            RequestDispatcher dispatcher = request.getRequestDispatcher(REQUESTS_JSP_PATH);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
