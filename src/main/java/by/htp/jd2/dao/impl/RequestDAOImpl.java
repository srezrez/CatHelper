package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.RequestDAO;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestDAOImpl implements RequestDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_REQUEST = "INSERT INTO request(date_request, date_issue, id_user_requester, id_cat, id_status) values ( ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_REQUEST = "select * from request where id_request = ?";
    private static final String SQL_DELETE_REQUEST = "delete from request where id_request = ?";
    private static final String SQL_UPDATE_REQUEST = "UPDATE request SET date_issue = ?, id_status = ? where id_request = ?";

    @Override
    public void add(Request request) throws DAOException {
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_REQUEST);

            ps.setDate(1, new java.sql.Date(request.getDateRequest().getTime()));
            ps.setDate(2, new java.sql.Date(request.getDateIssue().getTime()));
            ps.setInt(3, request.getRequester().getIdPk());
            ps.setInt(4, request.getCat().getIdPk());
            ps.setInt(5, request.getStatus().getIdPk());

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
}
