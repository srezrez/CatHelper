package by.htp.jd2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.htp.jd2.util.ConstantPool.*;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final CommandProvider provider = new CommandProvider();
       
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Locale usLocale = new Locale("en", "US");
		ResourceBundle bundle = ResourceBundle.getBundle("prop", usLocale);
		System.out.println(bundle.getString("prop.key1"));
		HttpSession session = request.getSession(false);
		if(session == null) {
			HttpSession sessionNew = request.getSession(true);
			//sessionNew.setAttribute("sessionExpired", true);
			response.sendRedirect(SIGN_IN_PAGE_REDIRECT);
		} else {
			String commandName = request.getParameter(COMMAND_PARAM);
			System.out.println("Command name = " + commandName);
			Command command = provider.getCommand(commandName);
			command.execute(request, response);
		}

	}
}
