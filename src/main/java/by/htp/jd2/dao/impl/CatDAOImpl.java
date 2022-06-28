package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.CatDAO;
import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.Breed;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.Gender;
import by.htp.jd2.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatDAOImpl implements CatDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_CAT = "INSERT INTO cat(name, birth_date, id_user_owner, id_breed, id_gender, description) values ( ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_CAT = "select * from cat where id_cat = ?";
    private static final String SQL_DELETE_CAT = "delete from cat where id_cat = ?";
    private static final String SQL_SELECT_ALL_CAT = "select * from cat";
    private static final String SQL_SELECT_ALL_FREE_CAT = "SELECT * FROM cathelper.cat where cathelper.cat.id_cat not in (select cathelper.request.id_cat from cathelper.request where cathelper.request.id_status = 2)";
    private static final String SQL_SELECT_ALL_ADDED_ACTIVE_CAT = "SELECT * FROM cathelper.cat where cathelper.cat.id_cat not in (select cathelper.request.id_cat from cathelper.request where cathelper.request.id_status = 2) and cathelper.cat.id_user_owner = ?";

    @Override
    public int add(Cat cat) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int idCat = 0;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_INSERT_CAT, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, cat.getName());
            ps.setDate(2, new java.sql.Date(cat.getBirthDate().getTime()));
            ps.setInt(3, cat.getOwner().getIdPk());
            ps.setInt(4, cat.getBreed().getIdPk());
            ps.setInt(5, cat.getGender().getIdPk());
            ps.setString(6, cat.getDescription());

            ps.executeUpdate();
            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idCat = generatedKeys.getInt(1);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in CatDao add");
        } finally {
            try {
                generatedKeys.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in CatDao add");
            }
        }
        return idCat;
    }

    @Override
    public void delete(int idPk) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_DELETE_CAT);
            ps.setInt(1,  idPk);
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in CatDao delete");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in CatDao delete");
            }
        }
    }

    @Override
    public Cat get(int idPk) throws DAOException {
        Cat cat = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_CAT);
            ps.setString(1, String.valueOf(idPk));
            rs = ps.executeQuery();
            while(rs.next()){
                cat = createCat(rs);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in CatDao get");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in CatDao get");
            }
        }
        return cat;
    }

    private Cat createCat(ResultSet rs) throws DAOException {
        Cat cat = new Cat();
        try {
            cat.setIdPk(rs.getInt("id_cat"));
            cat.setName(rs.getString("name"));
            cat.setBirthDate(new java.util.Date(rs.getDate("birth_date").getTime()));
            cat.setBreed(new Breed(rs.getInt("id_breed")));
            cat.setOwner(new User(rs.getInt("id_user_owner")));
            cat.setGender(Gender.getById(rs.getInt("id_gender")));
            cat.setDescription(rs.getString("description"));
        } catch (SQLException e) {
            throw new DAOException("Exception in CatDao createCat");
        }
        return cat;
    }

    @Override
    public void update(Cat entity) throws DAOException {

    }

    @Override
    public List<Cat> getAll() throws DAOException {
        List<Cat> cats = new ArrayList<Cat>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_CAT);
            while(rs.next()) {
                cats.add(createCat(rs));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in CatDao getAll");
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in CatDao getAll");
            }
        }
        return cats;
    }

    @Override
    public List<Cat> getAllFreeCats() throws DAOException {
        List<Cat> cats = new ArrayList<Cat>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_FREE_CAT);
            while(rs.next()) {
                cats.add(createCat(rs));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in CatDao getAllFreeCats");
        } catch (DAOException e) {
            throw new DAOException("Exception in CatDao getAllFreeCats");
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in CatDao getAllFreeCats");
            }
        }
        return cats;
    }

    @Override
    public List<Cat> getAllAddedActiveCats(int idUser) throws DAOException {
        List<Cat> cats = new ArrayList<Cat>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_ALL_ADDED_ACTIVE_CAT);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            while(rs.next()) {
                cats.add(createCat(rs));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in CatDao getAllAddedActiveCats");
        } catch (DAOException e) {
            throw new DAOException("Exception in CatDao getAllAddedActiveCats");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in CatDao getAllAddedActiveCats");
            }
        }
        return cats;
    }
}
