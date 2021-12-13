package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		String name = request.getParameter(NAME_PARAMETER);
		String surname = request.getParameter(SURNAME_PARAMETER);
		String email = request.getParameter(EMAIL_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		
		HttpSession session = request.getSession(true);
		session.setAttribute(USERNAME_ATTRIBUTE, name);
		session.setAttribute("role", "user");
		List<Book> books = new ArrayList<>();
		books.add(new Book("Java", 2000, new Date()));
		books.add(new Book("C++", 1000, new Date()));
		books.add(new Book("C#", 1500, new Date()));
		session.setAttribute("books", books);
		//System.out.println("===========books ================== " + request.getAttribute("books"));
		
		boolean flag = !email.equals("bad@bad.com");
		if(flag) {
			response.sendRedirect(INFO_PAGE_REDIRECT + "&" + INFO_MS_PARAMETER + "=" + SIGN_UP_SUCCESS_MESSAGE);
		} else {
			response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + SIGN_UP_FAIL_MESSAGE);
		}
		
	}

}
