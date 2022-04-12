package by.htp.jd2.controller;

import by.htp.jd2.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

import static by.htp.jd2.util.ConstantPool.*;

public final class CommandProvider {
	private Map<String, Command> commands = new HashMap<String, Command>();
	
	public CommandProvider() {
		commands.put(SIGN_IN_COMMAND, new SignInCommand());
		commands.put(SIGN_UP_COMMAND, new SignUpCommand());
		commands.put(GO_TO_SIGN_UP_PAGE_COMMAND, new GoToSignUpPageCommand());
		commands.put(GO_TO_SIGN_IN_PAGE_COMMAND, new GoToSignInPageCommand());
		commands.put(GO_TO_INDEX_PAGE_COMMAND, new GoToIndexPageCommand());
		commands.put(GO_TO_INFO_PAGE_COMMAND, new GoToInfoPageCommand());
		commands.put(GO_TO_MAIN_PAGE_COMMAND, new GoToHelloPageCommand());
		commands.put(GO_TO_ERROR_PAGE_COMMAND, new GoToErrorPageCommand());
		commands.put(GO_TO_ADD_CAT_PAGE_COMMAND, new GoToAddCatPageCommand());
		commands.put(GO_TO_REQUESTS_PAGE_COMMAND, new GoToRequestsPageCommand());
		commands.put(GO_TO_ADDED_CAT_LIST_PAGE_COMMAND, new GoToAddedCatListPageCommand());
		commands.put(ADD_CAT_COMMAND, new AddCatCommand());
		commands.put(GO_TO_CAT_PAGE_COMMAND, new GoToCatPageCommand());
		commands.put(SEND_REQUEST_COMMAND, new SendRequestCommand());
		commands.put(CANCEL_REQUEST_COMMAND, new CancelRequestCommand());
		commands.put(GO_TO_CAT_REQUEST_PAGE_COMMAND, new GoToCatRequestPage());
		commands.put(APPROVE_REQUEST_COMMAND, new ApproveRequestCommand());
		commands.put(REJECT_REQUEST_COMMAND, new RejectRequestCommand());
		commands.put(FILTER_REQUESTS_COMMAND, new FilterRequestsCommand());
		commands.put(FILTER_CAT_COMMAND, new FilterCatCommand());
		commands.put(GO_TO_PROFILE_PAGE_COMMAND, new GoToProfilePageCommand());
		commands.put(CHANGE_PASSWORD_COMMAND, new ChangePasswordCommand());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}

}
