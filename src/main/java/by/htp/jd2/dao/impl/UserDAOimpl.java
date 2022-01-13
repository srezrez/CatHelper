package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.User;

import java.sql.*;

public class UserDAOimpl implements UserDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_USER = "INSERT INTO user(name, surname, birthDate, email, password, idRole, idActivity) values ( ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_USER = "select * from user where idUser = ?";
    private static final String SQL_SELECT_USER_ALL = "select * from user";

    @Override
    public String authorization(String login, String password) throws DAOException {

        try {
            Connection con = connectionPool.takeConnection();
            con.close();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return "admin";
    }

    @Override
    public void add(User user) throws DAOException {

        try {
            Connection con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER);

            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getBirthDate().toString());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(6, String.valueOf(user.getRole().getIdPk()));
            ps.setString(7, String.valueOf(user.getActivity().getIdPk()));

            ps.executeUpdate();
            con.close();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(int idPk) {
    }

    @Override
    public User get(int idPk) throws DAOException {
        User user = null;
        try {
            Connection con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_USER);
            ps.setString(1, String.valueOf(idPk));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = createUser(rs);
            }
            con.close();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    private User createUser(ResultSet rs){
        User user = new User();
        try {
            user.setIdPk(rs.getInt("idUser"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setBirthDate(rs.getDate("birthDate"));
            user.setEmail(rs.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User entity) {

    }
}
