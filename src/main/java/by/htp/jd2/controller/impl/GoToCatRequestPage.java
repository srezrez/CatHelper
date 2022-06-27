package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.CatRequestViewModel;
import by.htp.jd2.service.CatService;
import by.htp.jd2.service.RequestService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.*;

public class GoToCatRequestPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ServiceFactory factory = ServiceFactory.getInstance();
        RequestService requestService = factory.getRequestService();
        CatService catService = factory.getCatService();
        CatRequestViewModel catRequest = requestService.getCatRequestInfo(Integer.parseInt(request.getParameter(ID_CAT_PARAMETER)));
        try {
            Cat cat = catService.getCat(Integer.parseInt(request.getParameter(ID_CAT_PARAMETER)));
            request.setAttribute("cat", cat);
            //session.setAttribute("catRequest", catRequest);
            request.setAttribute("catRequest", catRequest);
            RequestDispatcher dispatcher = request.getRequestDispatcher(CAT_REQUEST_JSP_PATH);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + BASIC_ERROR_MS);
        }
    }
}
