package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
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

	//public UserService userService = UserServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();

		String email = request.getParameter(EMAIL_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		
		HttpSession session = request.getSession(true);
		session.setAttribute(USERNAME_ATTRIBUTE, email);
		
		boolean flag = !email.equals("bad@bad.com");
		if(flag) {
			response.sendRedirect(MAIN_PAGE_REDIRECT);
		} else {
			response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + SIGN_IN_FAIL_MESSAGE);
		}
		
	}

}
