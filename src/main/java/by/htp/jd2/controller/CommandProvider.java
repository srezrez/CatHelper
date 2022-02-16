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
		commands.put(GO_TO_ADDED_PAGE_COMMAND, new GoToAddedPageCommand());
		commands.put(GO_TO_REQUESTS_PAGE_COMMAND, new GoToRequestsPageCommand());
		commands.put(GO_TO_ADDED_CAT_LIST_PAGE_COMMAND, new GoToAddedCatListPageCommand());
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}

}
