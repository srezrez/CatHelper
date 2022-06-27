package by.htp.jd2.controller.filter;

import by.htp.jd2.entity.Role;
import by.htp.jd2.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.htp.jd2.util.ConstantPool.*;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        String command = request.getParameter("command");
        if (command == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(!command.equals(GO_TO_SIGN_IN_PAGE_COMMAND) && !command.equals(GO_TO_SIGN_UP_PAGE_COMMAND) && !command.equals(GO_TO_INDEX_PAGE_COMMAND)
                && !command.equals(SIGN_IN_COMMAND) && !command.equals(SIGN_UP_COMMAND) && !command.equals(GO_TO_ERROR_PAGE_COMMAND)
                && session.getAttribute(USER_ATTRIBUTE) == null) {
            response.sendRedirect(INDEX_PAGE_REDIRECT);
            return;
        }

        if(command.equals(GO_TO_USERS_PAGE_COMMAND) && ((User)session.getAttribute(USER_ATTRIBUTE)).getRole() != Role.ADMIN) {
            response.sendRedirect(MAIN_PAGE_REDIRECT);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
