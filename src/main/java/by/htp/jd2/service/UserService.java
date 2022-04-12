package by.htp.jd2.service;

import by.htp.jd2.entity.User;

import java.util.List;

public interface UserService {

    User signIn(String email, String password) throws ServiceException;
    boolean signUp(User user) throws ServiceException;
    List<User> getAll() throws ServiceException;
    void changeActivity(int idUser) throws ServiceException;
    User get(int idUser);
}
