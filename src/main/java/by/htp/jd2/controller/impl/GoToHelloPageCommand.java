package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.Breed;
import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.service.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.htp.jd2.util.ConstantPool.*;

public class GoToHelloPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceFactory factory = ServiceFactory.getInstance();
		CatService catService = factory.getCatService();
		BreedService breedService = factory.getBreedService();
		try {
			List<CatListViewModel> catList = catService.getAllFreeCats();
			List<Breed> breedList = breedService.getAll();
			HttpSession session = request.getSession(true);
			session.setAttribute("catList", catList);
			request.setAttribute("breedList", breedList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_JSP_PATH);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + BASIC_ERROR_MS);
		}

		
	}

}
