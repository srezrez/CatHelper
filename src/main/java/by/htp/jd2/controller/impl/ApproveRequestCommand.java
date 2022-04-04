package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.service.RequestService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.ID_REQUEST_PARAMETER;
import static by.htp.jd2.util.ConstantPool.MAIN_PAGE_REDIRECT;

public class ApproveRequestCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RequestService requestService = serviceFactory.getRequestService();
        try {
            requestService.approveRequest(Integer.parseInt(request.getParameter(ID_REQUEST_PARAMETER)));
            response.sendRedirect(MAIN_PAGE_REDIRECT);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
