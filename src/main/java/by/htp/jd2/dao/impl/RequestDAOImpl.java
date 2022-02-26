package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.RequestDAO;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImpl implements RequestDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_REQUEST = "INSERT INTO request(date_request, date_issue, id_user_requester, id_cat, id_status) values ( ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_REQUEST = "select * from request where id_request = ?";
    private static final String SQL_DELETE_REQUEST = "delete from request where id_request = ?";
    private static final String SQL_UPDATE_REQUEST = "UPDATE request SET date_issue = ?, id_status = ? where id_request = ?";
    private static final String SQL_SELECT_ALL_REQUEST = "select * from request";
    private static final String SQL_SELECT_REQUEST_AMOUNT_BY_CAT = "select count(*) from request where id_cat = ?";

    @Override
    public int add(Request request) throws DAOException {
        Connection con = null;
        int idRequest = 0;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(request.getDateRequest().getTime()));
            ps.setDate(2, null);
            ps.setInt(3, request.getRequester().getIdPk());
            ps.setInt(4, request.getCat().getIdPk());
            ps.setInt(5, request.getStatus().getIdPk());

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idRequest = generatedKeys.getInt(1);
            }
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
        return idRequest;
    }

    @Override
    public void delete(int idPk) throws DAOException {

        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_DELETE_REQUEST);
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
    public Request get(int idPk) throws DAOException {
        Request request = null;
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_REQUEST);
            ps.setString(1, String.valueOf(idPk));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                request = createRequest(rs);
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
        return request;
    }

    private Request createRequest(ResultSet rs) {

        Request request = new Request();
        try {
            request.setIdPk(rs.getInt("id_request"));
            request.setDateRequest(rs.getDate("date_request"));
            request.setDateIssue(rs.getDate("date_issue"));
            request.setRequester(new User(rs.getInt("id_request")));
            request.setCat(new Cat(rs.getInt("id_cat")));
            request.setStatus(Status.getById(rs.getInt("id_status")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return request;
    }

    @Override
    public void update(Request request) throws DAOException {

        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_REQUEST);
            ps.setDate(1, new java.sql.Date(request.getDateIssue().getTime()));
            ps.setInt(2, request.getStatus().getIdPk());
            ps.setInt(3, request.getIdPk());
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
    public List<Request> getAll() throws DAOException {
        List<Request> requests = new ArrayList<Request>();
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_REQUEST);
            while(rs.next()) {
                requests.add(createRequest(rs));
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
        return requests;
    }

    @Override
    public int getRequestAmountByCatId(int idCat) throws DAOException {
        int amount = 0;
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_REQUEST_AMOUNT_BY_CAT);
            ps.setInt(1, idCat);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                amount = rs.getInt("count(*)");
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
        return amount;
    }
}
