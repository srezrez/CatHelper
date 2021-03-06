package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.INDEX_PAGE_REDIRECT;
import static by.htp.jd2.util.ConstantPool.USER_ATTRIBUTE;

public class LogOutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute(USER_ATTRIBUTE, null);
        response.sendRedirect(INDEX_PAGE_REDIRECT);
    }
}
