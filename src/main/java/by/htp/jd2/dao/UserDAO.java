package by.htp.jd2.dao;

import java.sql.SQLException;

public interface UserDAO {
    String authorization(String login, String password) throws DAOException;
}
