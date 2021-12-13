package by.htp.jd2.dao;

import by.htp.jd2.dao.impl.UserDAOimpl;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final UserDAO userDAO = new UserDAOimpl();

    private DAOFactory() {}

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

}
