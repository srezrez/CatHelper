package by.htp.jd2.dao;

import by.htp.jd2.entity.User;

import java.sql.SQLException;

public interface UserDAO extends BaseDAO<User>{
    String authorization(String login, String password) throws DAOException;
}
