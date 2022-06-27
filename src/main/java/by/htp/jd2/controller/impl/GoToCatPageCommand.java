package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.service.CatService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.*;

public class GoToCatPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        CatService catService = factory.getCatService();
        HttpSession session = request.getSession(true);
        try {
            CatListViewModel catInfo = catService.getCatInfo(Integer.parseInt(request.getParameter(ID_CAT_PARAMETER)));
            request.setAttribute("cat", catInfo);
            RequestDispatcher dispatcher = request.getRequestDispatcher(CAT_JSP_PATH);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + BASIC_ERROR_MS);
        }

    }
}
