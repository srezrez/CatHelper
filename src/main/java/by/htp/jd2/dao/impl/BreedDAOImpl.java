package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.BreedDAO;
import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.Breed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BreedDAOImpl implements BreedDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_BREED = "INSERT INTO breed(title) values (?)";
    private static final String SQL_DELETE_BREED = "delete from breed where id_breed = ?";
    private static final String SQL_SELECT_BREED = "select * from breed where id_breed = ?";


    @Override
    public void add(Breed breed) throws DAOException {
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_BREED);

            ps.setString(1, breed.getTitle());

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
    public void delete(int idPk) throws DAOException {

        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_DELETE_BREED);
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
    public Breed get(int idPk) throws DAOException {
        Breed breed = null;
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_BREED);
            ps.setString(1, String.valueOf(idPk));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                breed = new Breed(rs.getInt("id_breed"), rs.getString("title"));
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
        return breed;
    }

    @Override
    public void update(Breed entity) throws DAOException {

    }

    @Override
    public List<Breed> getAll() {
        return null;
    }
}
