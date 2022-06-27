package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.Breed;
import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.entity.Gender;
import by.htp.jd2.service.BreedService;
import by.htp.jd2.service.CatService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static by.htp.jd2.util.ConstantPool.BREED_PARAMETER;
import static by.htp.jd2.util.ConstantPool.MAIN_JSP_PATH;

public class FilterCatCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CatService catService = serviceFactory.getCatService();
        BreedService breedService = serviceFactory.getBreedService();
        String[] breedParams = request.getParameterValues(BREED_PARAMETER);
        String[] genderParams = request.getParameterValues("gender");
        List<Breed> breedList = breedParams!= null ? Arrays.stream(breedParams).map(x -> {
            return new Breed(Integer.parseInt(x));}).collect(Collectors.toList()) : null;
        List<Gender> genderList = genderParams != null ? Arrays.stream(genderParams).map(x -> {
            return Gender.getById(Integer.parseInt(x));}).collect(Collectors.toList()) : null;

        try {
            List<CatListViewModel> filteredCatList = catService.getAllFreeFilteredCats(breedList, genderList);
            List<Breed> breedListALL = breedService.getAll();
            HttpSession session = request.getSession(true);
            session.setAttribute("catList", filteredCatList);
            request.setAttribute("breedList", breedListALL);
            RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_JSP_PATH);
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
