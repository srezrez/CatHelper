package by.htp.jd2.service;

import by.htp.jd2.entity.UserInfo;

public interface UserService {

    String authorisation(String login, String password) throws ServiceException;
    boolean registration(UserInfo userInfo);
}
