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
    public int add(Breed breed) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int idBreed = 0;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_INSERT_BREED, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, breed.getTitle());
            ps.executeUpdate();
            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idBreed = generatedKeys.getInt(1);
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in BreedDao add");
        } finally {
            try {
                if(generatedKeys != null) generatedKeys.close();
                if(ps != null) ps.close();
                if(con != null)con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in BreedDao add");
            }
        }
        return idBreed;
    }

    @Override
    public void delete(int idPk) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_DELETE_BREED);
            ps.setInt(1,  idPk);
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in BreedDao delete");
        } finally {
            try {
                if(ps != null) ps.close();
                if(con != null)con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in BreedDao delete");
            }
        }
    }

    @Override
    public Breed get(int idPk) throws DAOException {
        Breed breed = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_BREED);
            ps.setString(1, String.valueOf(idPk));
            rs = ps.executeQuery();
            while(rs.next()){
                breed = new Breed(rs.getInt("id_breed"), rs.getString("title"));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in BreedDao get");
        } finally {
            try {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(con != null)con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in BreedDao get");
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
        Statement st = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_BREED);
            while(rs.next()) {
                breeds.add(new Breed(rs.getInt("id_breed"), rs.getString("title")));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in BreedDao update");
        } finally {
            try {
                if(rs != null) rs.close();
                if(st != null) st.close();
                if(con != null)con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in BreedDao update");
            }
        }
        return breeds;
    }
}
