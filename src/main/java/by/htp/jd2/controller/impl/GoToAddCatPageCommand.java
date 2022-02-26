package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.Breed;
import by.htp.jd2.service.BreedService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.htp.jd2.util.ConstantPool.ADD_CAT_JSP;

public class GoToAddCatPageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        BreedService breedService = factory.getBreedService();
        try {
            List<Breed> breeds = breedService.getAll();
            request.setAttribute("breedList", breeds);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ADD_CAT_JSP);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
