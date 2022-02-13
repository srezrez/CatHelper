package by.htp.jd2.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.entity.Activity;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User signIn(String email, String password) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getByEmail(email);
        if(user == null && !(BCrypt.verifyer().verify(password.toCharArray(), user.getPassword())).verified) {
            return null;
        }
        return user;
    }

    @Override
    public boolean signUp(User user) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            if(userDAO.getByEmail(user.getEmail()) != null)
                return false;
            userDAO.add(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception in sign up");
        }
        return true;
    }

    @Override
    public List<User> getAll() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("Exception in getAll");
        }
    }

    @Override
    public void changeActivity(int idUser) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            User user = userDAO.get(idUser);
            user.setActivity(user.getActivity().equals(Activity.ACTIVE) ? Activity.BLOCKED : Activity.ACTIVE);
            userDAO.updateActivity(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception in changeActivity");
        }
    }
}
