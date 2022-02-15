package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.service.CatService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.UserService;

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
		try {
			List<CatListViewModel> catList = catService.getAllFreeCats();
			HttpSession session = request.getSession(true);
			session.setAttribute("catList", catList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_JSP_PATH);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		
	}

}
