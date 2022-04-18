package by.htp.jd2.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String command = request.getParameter("command");
        if (command == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(!command.equals(GO_TO_SIGN_IN_PAGE_COMMAND) && !command.equals(GO_TO_SIGN_IN_PAGE_COMMAND) && !command.equals(GO_TO_INDEX_PAGE_COMMAND)
                && !command.equals(SIGN_IN_COMMAND) && !command.equals(SIGN_UP_COMMAND)
                && request.getSession(true).getAttribute(USER_ATTRIBUTE) == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(INDEX_PAGE_REDIRECT);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
