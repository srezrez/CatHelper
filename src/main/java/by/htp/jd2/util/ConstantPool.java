package by.htp.jd2.util;

public class ConstantPool {
	
	//command
	public static final String COMMAND_PARAM = "command";
	public static final String SIGN_IN_COMMAND = "sign-in";
	public static final String SIGN_UP_COMMAND = "sign-up";
	public static final String GO_TO_SIGN_UP_PAGE_COMMAND = "GO_TO_SIGN_UP_PAGE";
	public static final String GO_TO_SIGN_IN_PAGE_COMMAND = "GO_TO_SIGN_IN_PAGE";
	public static final String GO_TO_INDEX_PAGE_COMMAND = "GO_TO_INDEX_PAGE";
	public static final String GO_TO_INFO_PAGE_COMMAND = "GO_TO_INFO_PAGE";
	public static final String GO_TO_MAIN_PAGE_COMMAND = "GO_TO_MAIN_PAGE";
	public static final String GO_TO_ERROR_PAGE_COMMAND = "GO_TO_ERROR_PAGE";
	
	//path
	public static final String ERROR_JSP_PATH = "/WEB-INF/jsp/error.jsp";
	public static final String MAIN_JSP_PATH = "/WEB-INF/jsp/main.jsp";
	public static final String INDEX_JSP_PATH = "index.jsp";
	public static final String INFO_JSP_PATH = "/WEB-INF/jsp/info.jsp";
	public static final String SIGN_IN_JSP_PATH = "/WEB-INF/jsp/sign-in.jsp";
	public static final String SIGN_UP_JSP_PATH = "/WEB-INF/jsp/sign-up.jsp";
	
	//parameter
	public static final String NAME_PARAMETER = "name";
	public static final String SURNAME_PARAMETER = "surname";
	public static final String EMAIL_PARAMETER = "email";
	public static final String PASSWORD_PARAMETER = "password";
	public static final String ERROR_MS_PARAMETER = "error-ms";
	public static final String INFO_MS_PARAMETER = "info-message";
	
	//attribute
	public static final String USERNAME_ATTRIBUTE = "username";
	
	//redirect
	public static final String MAIN_PAGE_REDIRECT = "MyController?command=GO_TO_MAIN_PAGE";
	public static final String ERROR_PAGE_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";
	public static final String INFO_PAGE_REDIRECT = "MyController?command=GO_TO_INFO_PAGE";
	public static final String INDEX_PAGE_REDIRECT = "MyController?command=GO_TO_INDEX_PAGE";
	public static final String SIGN_IN_PAGE_REDIRECT = "MyController?command=GO_TO_SIGN_IN_PAGE";
	
	//message
	public static final String SIGN_IN_FAIL_MESSAGE = "Something went wrong. Logination failed";
	public static final String SIGN_UP_FAIL_MESSAGE = "Something went wrong. Registration failed";
	public static final String SIGN_UP_SUCCESS_MESSAGE = "You've created new account!";

	//db parameters
	public static final String DB_DRIVER = "db.driver";
	public static final String DB_URL = "db.url";
	public static final String DB_USER = "db.user";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_POOL_SIZE = "db.poolsize";
}
