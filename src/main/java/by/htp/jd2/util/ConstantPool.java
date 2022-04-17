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
	public static final String GO_TO_REQUESTS_PAGE_COMMAND = "GO_TO_REQUESTS_PAGE";
	public static final String GO_TO_ADDED_CAT_LIST_PAGE_COMMAND = "GO_TO_ADDED_CAT_LIST_PAGE";
	public static final String GO_TO_ADD_CAT_PAGE_COMMAND = "GO_TO_ADD_CAT_PAGE";
	public static final String ADD_CAT_COMMAND = "add-cat";
	public static final String GO_TO_CAT_PAGE_COMMAND = "GO_TO_CAT_PAGE";
	public static final String SEND_REQUEST_COMMAND = "SEND_REQUEST";
	public static final String CANCEL_REQUEST_COMMAND = "CANCEL_REQUEST";
	public static final String GO_TO_CAT_REQUEST_PAGE_COMMAND = "GO_TO_CAT_REQUEST_PAGE";
	public static final String APPROVE_REQUEST_COMMAND = "APPROVE_REQUEST";
	public static final String REJECT_REQUEST_COMMAND = "REJECT_REQUEST";
	public static final String FILTER_REQUESTS_COMMAND = "FILTER_REQUESTS";
	public static final String FILTER_CAT_COMMAND = "FILTER_CAT";
	public static final String GO_TO_PROFILE_PAGE_COMMAND = "GO_TO_PROFILE_PAGE";
	public static final String CHANGE_PASSWORD_COMMAND = "CHANGE_PASSWORD";
	public static final String GO_TO_USERS_PAGE_COMMAND = "GO_TO_USERS_PAGE";
	public static final String CHANGE_USER_ACTIVITY_COMMAND = "CHANGE_USER_ACTIVITY";
	public static final String CHANGE_LOCAL_COMMAND = "CHANGE_LOCAL";
	public static final String LOG_OUT_COMMAND = "LOG_OUT";
	
	//path
	public static final String ERROR_JSP_PATH = "/WEB-INF/jsp/error.jsp";
	public static final String MAIN_JSP_PATH = "/WEB-INF/jsp/main.jsp";
	public static final String INDEX_JSP_PATH = "index.jsp";
	public static final String INFO_JSP_PATH = "/WEB-INF/jsp/info.jsp";
	public static final String SIGN_IN_JSP_PATH = "/WEB-INF/jsp/sign-in.jsp";
	public static final String SIGN_UP_JSP_PATH = "/WEB-INF/jsp/sign-up.jsp";
	public static final String ADD_CAT_JSP = "/WEB-INF/jsp/add-cat.jsp";
	public static final String REQUESTS_JSP_PATH = "/WEB-INF/jsp/requests.jsp";
	public static final String ADDED_CAT_LIST_JSP_PATH = "/WEB-INF/jsp/added-cat-list.jsp";
	public static final String CAT_JSP_PATH = "/WEB-INF/jsp/cat.jsp";
	public static final String CAT_REQUEST_JSP_PATH = "/WEB-INF/jsp/cat-request.jsp";
	public static final String REQUESTS_TABLE_JSP_PATH = "/WEB-INF/jsp/requests-table.jsp";
	public static final String USER_INFO_JSP_PATH = "/WEB-INF/jsp/user-info.jsp";
	public static final String USER_LIST_JSP_PATH = "/WEB-INF/jsp/user-list.jsp";
	
	//parameter
	public static final String NAME_PARAMETER = "name";
	public static final String SURNAME_PARAMETER = "surname";
	public static final String BIRTH_DATE_PARAMETER = "birth-date";
	public static final String EMAIL_PARAMETER = "email";
	public static final String PASSWORD_PARAMETER = "password";
	public static final String ERROR_MS_PARAMETER = "error-ms";
	public static final String INFO_MS_PARAMETER = "info-message";
	public static final String ID_CAT_PARAMETER = "id-cat";
	public static final String ID_REQUEST_PARAMETER = "id-request";
	public static final String STATUS_PARAMETER = "status";
	public static final String BREED_PARAMETER = "breed";
	
	//attribute
	public static final String USERNAME_ATTRIBUTE = "username";
	
	//redirect
	public static final String MAIN_PAGE_REDIRECT = "MyController?command=GO_TO_MAIN_PAGE";
	public static final String ERROR_PAGE_REDIRECT = "MyController?command=GO_TO_ERROR_PAGE";
	public static final String INFO_PAGE_REDIRECT = "MyController?command=GO_TO_INFO_PAGE";
	public static final String INDEX_PAGE_REDIRECT = "MyController?command=GO_TO_INDEX_PAGE";
	public static final String SIGN_IN_PAGE_REDIRECT = "MyController?command=GO_TO_SIGN_IN_PAGE";
	public static final String USER_LIST_PAGE_REDIRECT = "MyController?command=GO_TO_USERS_PAGE";
	
	//message
	public static final String SIGN_IN_FAIL_MESSAGE = "Incorrect email or password. Please try again";
	public static final String SIGN_UP_FAIL_MESSAGE = "Something went wrong. Registration failed";
	public static final String SIGN_UP_USER_EXISTS_MESSAGE = "User with this email already exists";
	public static final String SIGN_UP_SUCCESS_MESSAGE = "You've created new account!";
	public static final String VALIDATION_ERROR_MS = "Fill the form again with correct values.";

	//db parameters
	public static final String DB_DRIVER = "db.driver";
	public static final String DB_URL = "db.url";
	public static final String DB_USER = "db.user";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_POOL_SIZE = "db.poolsize";

	//validation
	public static final int EMAIL_MIN = 7;
	public static final int EMAIL_MAX = 51;
	public static final int PASSWORD_MIN = 9;
	public static final int PASSWORD_MAX = 26;
	public static final int NAME_MIN = 1;
	public static final int NAME_MAX = 26;

	//file path
	public static final String PHOTO_SAVE_PATH = "D:\\Projects\\IdeaProjects\\CatHelper\\catHelper\\src\\main\\webapp\\resources\\img\\";
	public static final String PHOTO_PATH_DB = "resources/img/";
}
