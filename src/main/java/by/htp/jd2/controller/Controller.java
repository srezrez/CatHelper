package by.htp.jd2.controller;

import by.htp.jd2.dao.util.DBResourceManager;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
	//private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	//private UserService userService = serviceFactory.getUserService();

	public Controller() {
        super();
        // TODO Auto-generated constructor stub
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

//		Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection con = null;
//		Statement st = null;
//		ResultSet rs = null;
//
//		try{
//			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test?useSSL=false", "root", "root");
//			System.out.println("OK!");
//
//		} catch (SQLException throwables) {
//			throwables.printStackTrace();
//		}

//		DBResourceManager dbResourceManager = new DBResourceManager();
//		System.out.println("===============" + dbResourceManager.getValue("db.user") + "================");

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		try {
			serviceFactory.getUserService().signIn("login", "pass");
		} catch (ServiceException e) {
			e.printStackTrace();
		}

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
