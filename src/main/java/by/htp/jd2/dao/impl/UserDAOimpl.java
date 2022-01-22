package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.Activity;
import by.htp.jd2.entity.Role;
import by.htp.jd2.entity.User;

import java.sql.*;

public class UserDAOimpl implements UserDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_USER = "INSERT INTO user(name, surname, birth_date, email, password, id_role, id_activity) values ( ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_USER = "select * from user where id_user = ?";
    private static final String SQL_DELETE_USER = "delete from user where id_user = ?";
    private static final String SQL_UPDATE_USER = "UPDATE user SET password = ? where idUser = ?";

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

        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER);

            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setDate(3, new java.sql.Date(user.getBirthDate().getTime()));
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole().getIdPk());
            ps.setInt(7, user.getRole().getIdPk());

            ps.executeUpdate();
            con.close();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int idPk) throws DAOException {
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_DELETE_USER);
            ps.setInt(1,  idPk);
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public User get(int idPk) throws DAOException  {
        User user = null;
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
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
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
        return user;
    }

    private User createUser(ResultSet rs){
        User user = new User();
        try {
            user.setIdPk(rs.getInt("id_user"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setBirthDate(rs.getDate("birth_date"));
            user.setEmail(rs.getString("email"));
            user.setRole(Role.getById(rs.getInt("id_role")));
            user.setActivity(Activity.getById(rs.getInt("id_activity")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User entity) throws DAOException {
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_USER);
            ps.setString(1, entity.getPassword());
            ps.setInt(2, entity.getIdPk());
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }

    }
}
