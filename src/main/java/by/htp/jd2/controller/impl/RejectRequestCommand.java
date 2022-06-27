package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.service.RequestService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.*;

public class RejectRequestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RequestService requestService = serviceFactory.getRequestService();
        try {
            requestService.rejectRequest(Integer.parseInt(request.getParameter(ID_REQUEST_PARAMETER)));
            response.sendRedirect(MAIN_PAGE_REDIRECT);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + BASIC_ERROR_MS);
        }
    }
}
