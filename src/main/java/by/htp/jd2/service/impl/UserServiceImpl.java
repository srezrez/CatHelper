package by.htp.jd2.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User signIn(String email, String password) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getByEmail(email);
        if(user != null && (BCrypt.verifyer().verify(password.toCharArray(), user.getPassword())).verified) {
            return user;
        }
        return null;
    }

    @Override
    public boolean signUp(User user) {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            if(userDAO.getByEmail(user.getEmail()) != null)
                return false;
            userDAO.add(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
