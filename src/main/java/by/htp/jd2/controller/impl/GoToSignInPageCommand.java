package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.htp.jd2.util.ConstantPool.*;

public class GoToSignInPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(SIGN_IN_JSP_PATH);
		dispatcher.forward(request, response);
		
	}
}
