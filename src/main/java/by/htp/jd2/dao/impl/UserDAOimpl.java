package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.entity.User;

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

    @Override
    public void add(User entity) {

    }

    @Override
    public void delete(int idPk) {

    }

    @Override
    public User get(int idPk) {
        return null;
    }

    @Override
    public void update(User entity) {

    }
}
