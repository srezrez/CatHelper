package by.htp.jd2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.htp.jd2.util.ConstantPool.*;

@MultipartConfig
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final CommandProvider provider = new CommandProvider();

	public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {

		HttpSession session = request.getSession(false);
		if(session == null) {
			HttpSession sessionNew = request.getSession(true);
			response.sendRedirect(SIGN_IN_PAGE_REDIRECT);
			return;
		}
		StringBuffer urll = request.getRequestURL();
		String urii = request.getRequestURI();
		String commandName = request.getParameter(COMMAND_PARAM);
		Command command = provider.getCommand(commandName);
		command.execute(request, response);

	}
}
