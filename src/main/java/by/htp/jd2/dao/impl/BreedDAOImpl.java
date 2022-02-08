package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.BreedDAO;
import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.Breed;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BreedDAOImpl implements BreedDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_BREED = "INSERT INTO breed(title) values (?)";
    private static final String SQL_DELETE_BREED = "delete from breed where id_breed = ?";
    private static final String SQL_SELECT_BREED = "select * from breed where id_breed = ?";
    private static final String SQL_SELECT_ALL_BREED = "select * from breed";


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
    public List<Breed> getAll() throws DAOException {
        List<Breed> breeds = new ArrayList<Breed>();
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_BREED);
            while(rs.next()) {
                breeds.add(new Breed(rs.getInt("id_breed"), rs.getString("title")));
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
        return breeds;
    }
}
