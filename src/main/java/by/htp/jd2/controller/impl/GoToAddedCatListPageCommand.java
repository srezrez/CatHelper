package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.service.CatService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.htp.jd2.util.ConstantPool.ADDED_CAT_LIST_JSP_PATH;

public class GoToAddedCatListPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ServiceFactory factory = ServiceFactory.getInstance();
        CatService catService = factory.getCatService();
        List<CatListViewModel> catList = null;
        try {
            catList = catService.getAddedCats((int)session.getAttribute("idUser"));
            session.setAttribute("addedCatList", catList);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ADDED_CAT_LIST_JSP_PATH);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
