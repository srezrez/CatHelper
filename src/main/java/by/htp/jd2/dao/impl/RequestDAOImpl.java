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
    private static final String SQL_SELECT_REQUEST_AMOUNT_BY_CAT = "select count(*) from request where id_cat = ? and id_status = 1";
    private static final String SQL_SELECT_REQUESTS_BY_USER_ID = "SELECT * FROM cathelper.request where id_user_requester = ? and id_status = ?";
    private static final String SQL_SELECT_REQUEST_QUEUE_AMOUNT = "SELECT count(*) FROM cathelper.request where id_cat = ? and id_status = 1  and date_request <= ?";
    private static final String SQL_CANCEL_REQUEST = "UPDATE request SET id_status = ? where id_request = ?";
    private static final String SQL_SELECT_ACTIVE_REQUEST_AMOUNT_FOR_CAT = "SELECT count(*) FROM cathelper.request where id_status = 1 and id_cat = ?";
    private static final String SQL_SELECT_FIRST_ACTIVE_REQUEST = "SELECT * FROM cathelper.request where id_status = 1 and id_cat = ? order by date_request LIMIT 1";
    private static final String SQL_SELECT_ACTIVE_REQUEST_BY_CAT_AND_USER = "SELECT * FROM cathelper.request where id_status = 1 and id_cat = ? and id_user_requester = ?";

    @Override
    public int add(Request request) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int idRequest = 0;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(request.getDateRequest().getTime()));
            ps.setDate(2, null);
            ps.setInt(3, request.getRequester().getIdPk());
            ps.setInt(4, request.getCat().getIdPk());
            ps.setInt(5, request.getStatus().getIdPk());

            ps.executeUpdate();
            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idRequest = generatedKeys.getInt(1);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao add");
        } finally {
            try {
                generatedKeys.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao add");
            }
        }
        return idRequest;
    }

    @Override
    public void delete(int idPk) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_DELETE_REQUEST);
            ps.setInt(1,  idPk);
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in RequestDao delete");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao delete");
            }
        }
    }

    @Override
    public Request get(int idPk) throws DAOException {
        Request request = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_REQUEST);
            ps.setString(1, String.valueOf(idPk));
            rs = ps.executeQuery();
            while(rs.next()){
                request = createRequest(rs);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao get");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao get");
            }
        }
        return request;
    }

    private Request createRequest(ResultSet rs) throws DAOException {

        Request request = new Request();
        try {
            request.setIdPk(rs.getInt("id_request"));
            request.setDateRequest(rs.getDate("date_request"));
            request.setDateIssue(rs.getDate("date_issue"));
            request.setRequester(new User(rs.getInt("id_user_requester")));
            request.setCat(new Cat(rs.getInt("id_cat")));
            request.setStatus(Status.getById(rs.getInt("id_status")));

        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao createRequest");
        }
        return request;
    }

    @Override
    public void update(Request request) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_UPDATE_REQUEST);
            ps.setTimestamp(1, new Timestamp(request.getDateRequest().getTime()));
            ps.setInt(2, request.getStatus().getIdPk());
            ps.setInt(3, request.getIdPk());
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in RequestDao update");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao update");
            }
        }
    }

    @Override
    public List<Request> getAll() throws DAOException {
        List<Request> requests = new ArrayList<Request>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_REQUEST);
            while(rs.next()) {
                requests.add(createRequest(rs));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao getAll");
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao getAll");
            }
        }
        return requests;
    }

    @Override
    public int getRequestAmountByCatId(int idCat) throws DAOException {
        int amount = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_ACTIVE_REQUEST_AMOUNT_FOR_CAT);
            ps.setInt(1, idCat);
            rs = ps.executeQuery();
            while(rs.next()){
                amount = rs.getInt("count(*)");
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao getRequestAmountByCatId");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao getRequestAmountByCatId");
            }
        }
        return amount;
    }

    @Override
    public List<Request> getRequestsByUserIdAndStatus(int idUser, int idStatus) throws DAOException {
        List<Request> requests = new ArrayList<Request>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_REQUESTS_BY_USER_ID);
            ps.setInt(1, idUser);
            ps.setInt(2, idStatus);
            rs = ps.executeQuery();
            while(rs.next()) {
                requests.add(createRequest(rs));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao getRequestsByUserIdAndStatus");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao getRequestsByUserIdAndStatus");
            }
        }
        return requests;
    }

    @Override
    public int getQueueAmount(int idCat, java.util.Date requestDate) throws DAOException {
        int amount = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_REQUEST_QUEUE_AMOUNT);
            ps.setInt(1, idCat);
            ps.setTimestamp(2, new Timestamp(requestDate.getTime()));
            rs = ps.executeQuery();
            while(rs.next()){
                amount = rs.getInt("count(*)");
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao getQueueAmount");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao getQueueAmount");
            }
        }
        return amount;
    }

    @Override
    public void cancelRequest(int idRequest) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_CANCEL_REQUEST);
            ps.setInt(1, Status.REQUEST_CANCELED.getIdPk());
            ps.setInt(2, idRequest);
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in RequestDao cancelRequest");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao cancelRequest");
            }
        }
    }

    @Override
    public int getRequestQueueAmount(int idCat) throws DAOException {
        int amount = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_ACTIVE_REQUEST_AMOUNT_FOR_CAT);
            ps.setInt(1, idCat);
            rs = ps.executeQuery();
            while(rs.next()){
                amount = rs.getInt("count(*)");
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao getRequestQueueAmount");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao getRequestQueueAmount");
            }
        }
        return amount;
    }

    @Override
    public Request getFirstActiveRequestByCat(int idCat) throws DAOException {
        Request request = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_FIRST_ACTIVE_REQUEST);
            ps.setInt(1, idCat);
            rs = ps.executeQuery();
            while(rs.next()){
                request = createRequest(rs);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao getFirstActiveRequestByCat");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao getFirstActiveRequestByCat");
            }
        }
        return request;
    }

    @Override
    public Request getActiveRequestByCatAndUser(int idCat, int idUser) throws DAOException {
        Request request = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_ACTIVE_REQUEST_BY_CAT_AND_USER);
            ps.setInt(1, idCat);
            ps.setInt(2, idUser);
            rs = ps.executeQuery();
            while(rs.next()){
                request = createRequest(rs);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in RequestDao getActiveRequestByCatAndUser");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in RequestDao getActiveRequestByCatAndUser");
            }
        }
        return request;
    }
}
