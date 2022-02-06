package by.htp.jd2.service.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String signIn(String login, String password) throws ServiceException {

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

        try {
            User user = userDAO.get(1);
        } catch (DAOException e) {
            throw new ServiceException();
        }
        //1 Validation

        //2 Realization

        return "admin";
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
