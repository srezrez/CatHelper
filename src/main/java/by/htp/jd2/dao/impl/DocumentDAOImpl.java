package by.htp.jd2.dao.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DocumentDAO;
import by.htp.jd2.dao.connectionpool.ConnectionPool;
import by.htp.jd2.dao.connectionpool.ConnectionPoolException;
import by.htp.jd2.entity.*;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAOImpl implements DocumentDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SQL_INSERT_DOCUMENT = "INSERT INTO document(id_document, path, description, id_cat, id_document_type) values ( ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_DOCUMENT = "select * from document where id_document = ?";
    private static final String SQL_DELETE_DOCUMENT = "delete from document where id_document = ?";
    //private static final String SQL_UPDATE_DOCUMENT = "UPDATE document SET password = ? where idUser = ?";
    private static final String SQL_SELECT_ALL_DOCUMENT = "select * from document";


    @Override
    public void add(Document document) throws DAOException {

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

        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_DELETE_DOCUMENT);
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
    public Document get(int idPk) throws DAOException {
        Document document = null;
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_DOCUMENT);
            ps.setString(1, String.valueOf(idPk));
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                document = createDocument(rs);
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
        return document;
    }

    private Document createDocument(ResultSet rs) {

        Document document = new Document();
        try {
            document.setPath(rs.getString("path"));
            document.setDescription(rs.getString("description"));
            document.setCat(new Cat(rs.getInt("id_cat")));
            document.setDocumentType(DocumentType.getById(rs.getInt("id_document_type")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return document;
    }

    @Override
    public void update(Document entity) throws DAOException {

    }

    @Override
    public List<Document> getAll() throws DAOException {
        List<Document> documents = new ArrayList<Document>();
        Connection con = null;
        try {
            con = connectionPool.takeConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_DOCUMENT);
            while(rs.next()) {
                documents.add(createDocument(rs));
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
        return documents;
    }
}
