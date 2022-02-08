package by.htp.jd2.service;

import by.htp.jd2.entity.User;

import java.util.List;

public interface UserService {

    User signIn(String email, String password) throws ServiceException;
    boolean signUp(User user);
    List<User> getAll();
}
