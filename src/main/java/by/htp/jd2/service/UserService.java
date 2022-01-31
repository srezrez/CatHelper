package by.htp.jd2.service;

import by.htp.jd2.entity.User;

public interface UserService {

    String signIn(String login, String password) throws ServiceException;
    boolean signUp(User userInfo);
}
