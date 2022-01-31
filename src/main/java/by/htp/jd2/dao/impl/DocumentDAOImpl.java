package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DocumentDAO;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DocumentDAOImpl implements DocumentDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_DOCUMENT = "INSERT INTO document(id_document, path, description, id_cat, id_document_type) values ( ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_DOCUMENT = "select * from document where id_document = ?";
    private static final String SQL_DELETE_DOCUMENT = "delete from document where id_document = ?";
    //private static final String SQL_UPDATE_DOCUMENT = "UPDATE document SET password = ? where idUser = ?";


    @Override
    public void add(Document document) throws DAOException {

        //закрывать ps
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_DOCUMENT);

            ps.setString(1, document.getPath());
            ps.setString(2, document.getDescription());
            ps.setInt(3, document.getCat().getIdPk());
            ps.setInt(4, document.getDocumentType().getIdPk());
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

    }

    @Override
    public Document get(int idPk) throws DAOException {
        return null;
    }

    @Override
    public void update(Document entity) throws DAOException {

    }
}
