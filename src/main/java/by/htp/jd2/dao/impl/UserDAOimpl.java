package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.UserDAO;

import java.sql.SQLException;

public class UserDAOimpl implements UserDAO {
    @Override
    public String authorization(String login, String password) throws DAOException {


        //JDBC
        int x = 2;
        if(x > 10) {
            try {
                throw new SQLException();
            } catch (SQLException e) {
                throw new DAOException();
            }
        }
        return "admin";
    }
}
