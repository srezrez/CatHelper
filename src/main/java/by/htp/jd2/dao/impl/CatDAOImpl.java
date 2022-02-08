package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.CatDAO;
import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.Breed;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.Gender;
import by.htp.jd2.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CatDAOImpl implements CatDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_CAT = "INSERT INTO user(name, birth_date, id_user_owner, id_breed, id_gender) values ( ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_CAT = "select * from cat where id_cat = ?";
    private static final String SQL_DELETE_CAT = "delete from cat where id_cat = ?";
    //private static final String SQL_UPDATE_CAT = "UPDATE cat SET password = ? where idUser = ?";

    @Override
    public void add(Cat cat) throws DAOException {

        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_CAT);

            ps.setString(1, cat.getName());
            ps.setDate(2, new java.sql.Date(cat.getBirthDate().getTime()));
            ps.setInt(3, cat.getOwner().getIdPk());
            ps.setInt(4, cat.getBreed().getIdPk());
            ps.setInt(5, cat.getGender().getIdPk());

            ps.executeUpdate();
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
            PreparedStatement ps = con.prepareStatement(SQL_DELETE_CAT);
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
    public Cat get(int idPk) throws DAOException {
        Cat cat = null;
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_CAT);
            ps.setString(1, String.valueOf(idPk));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cat = createCat(rs);
            }
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
        return cat;
    }

    private Cat createCat(ResultSet rs) throws DAOException {
        Cat cat = new Cat();
        try {
            cat.setIdPk(rs.getInt("id_cat"));
            cat.setName(rs.getString("name"));
            cat.setBirthDate(rs.getDate("birth_date"));
            cat.setBreed(new Breed(rs.getInt("id_breed")));
            cat.setOwner(new User(rs.getInt("id_user_owner")));
            cat.setGender(Gender.getById(rs.getInt("id_gender")));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return cat;
    }

    @Override
    public void update(Cat entity) throws DAOException {

    }

    @Override
    public List<Cat> getAll() {
        return null;
    }
}
