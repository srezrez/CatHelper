package by.htp.jd2.controller.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.htp.jd2.controller.Command;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.htp.jd2.util.ConstantPool.*;

public class SignInCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		String email = request.getParameter(EMAIL_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		try {
			userService.signIn(email, password);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.sendRedirect(MAIN_PAGE_REDIRECT);
		
	}

}
