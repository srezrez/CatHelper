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
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_USER = "INSERT INTO user(name, surname, birth_date, email, password, id_role, id_activity) values ( ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_USER = "select * from user where id_user = ?";
    private static final String SQL_DELETE_USER = "delete from user where id_user = ?";
    private static final String SQL_UPDATE_USER = "UPDATE user SET password = ? where id_user = ?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "select * from user where email = ?";
    private static final String SQL_SELECT_ALL_USER = "select * from user";
    private static final String SQL_UPDATE_USER_ACTIVITY = "UPDATE user SET id_activity = ? where id_user = ?";

    @Override
    public int add(User user) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int idUser = 0;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setDate(3, new java.sql.Date(user.getBirthDate().getTime()));
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole().getIdPk());
            ps.setInt(7, user.getRole().getIdPk());

            ps.executeUpdate();
            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idUser = generatedKeys.getInt(1);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in UserDao add");
        } finally {
            try {
                generatedKeys.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in UserDao add");
            }
        }
        return idUser;
    }

    @Override
    public void delete(int idPk) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_DELETE_USER);
            ps.setInt(1,  idPk);
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in UserDao delete");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in UserDao delete");
            }
        }
    }

    @Override
    public User get(int idPk) throws DAOException  {
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_USER);
            ps.setString(1, String.valueOf(idPk));
            rs = ps.executeQuery();
            while(rs.next()){
                user = createUser(rs);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in UserDao get");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in UserDao get");
            }
        }
        return user;
    }

    private User createUser(ResultSet rs) throws DAOException {
        User user = new User();
        try {
            user.setIdPk(rs.getInt("id_user"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setBirthDate(rs.getDate("birth_date"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.getById(rs.getInt("id_role")));
            user.setActivity(Activity.getById(rs.getInt("id_activity")));
        } catch (SQLException e) {
            throw new DAOException("Exception in UserDao createUser");
        }
        return user;
    }

    @Override
    public void update(User entity) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_UPDATE_USER);
            ps.setString(1, entity.getPassword());
            ps.setInt(2, entity.getIdPk());
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in UserDao update");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in UserDao update");
            }
        }

    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users = new ArrayList<User>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_USER);
            while(rs.next()) {
                users.add(createUser(rs));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in UserDao getAll");
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in UserDao getAll");
            }
        }
        return users;
    }

    @Override
    public User getByEmail(String email) throws DAOException {
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while(rs.next()){
                user = createUser(rs);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in UserDao getByEmail");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in UserDao getByEmail");
            }
        }
        return user;
    }

    @Override
    public void updateActivity(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_UPDATE_USER_ACTIVITY);
            ps.setInt(1, user.getActivity().getIdPk());
            ps.setInt(2, user.getIdPk());
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in UserDao updateActivity");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in UserDao updateActivity");
            }
        }
    }
}
