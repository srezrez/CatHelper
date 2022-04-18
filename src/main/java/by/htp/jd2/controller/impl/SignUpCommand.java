package by.htp.jd2.controller.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.Activity;
import by.htp.jd2.entity.Role;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.htp.jd2.util.ConstantPool.*;

public class SignUpCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory factory = ServiceFactory.getInstance();
		UserService userService = factory.getUserService();
		User user = null;
		try {
			user = new User(request.getParameter(NAME_PARAMETER), request.getParameter(SURNAME_PARAMETER),
					new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter(BIRTH_DATE_PARAMETER)),
					request.getParameter(EMAIL_PARAMETER), request.getParameter(PASSWORD_PARAMETER), Role.USER, Activity.ACTIVE);
			boolean result = userService.signUp(user);
			if(result) {
				response.sendRedirect(INFO_PAGE_REDIRECT + "&" + INFO_MS_PARAMETER + "=" + SIGN_UP_SUCCESS_MESSAGE);
			} else {
				response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + SIGN_UP_USER_EXISTS_MESSAGE);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

}
