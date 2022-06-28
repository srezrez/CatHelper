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

    private static final String SQL_INSERT_DOCUMENT = "INSERT INTO document(path, description, id_cat, id_document_type) values ( ?, ?, ?, ?)";
    private static final String SQL_SELECT_DOCUMENT = "select * from document where id_document = ?";
    private static final String SQL_DELETE_DOCUMENT = "delete from document where id_document = ?";
    private static final String SQL_SELECT_ALL_DOCUMENT = "select * from document";
    private static final String SQL_SELECT_ALL_FREE_CAT_PHOTO = "SELECT * FROM cathelper.document where cathelper.document.id_cat not in (select cathelper.request.id_cat from cathelper.request where cathelper.request.id_status = 2) and cathelper.document.id_document_type = 1";
    private static final String SQL_SELECT_DOCUMENT_BY_CAT_ID = "select * from document where id_cat = ?";

    @Override
    public int add(Document document) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        int docId = 0;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_INSERT_DOCUMENT, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, document.getPath());
            ps.setString(2, document.getDescription());
            ps.setInt(3, document.getCat().getIdPk());
            ps.setInt(4, document.getDocumentType().getIdPk());
            ps.executeUpdate();
            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                docId = generatedKeys.getInt(1);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in DocumentDao add");
        } finally {
            try {
                generatedKeys.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in DocumentDao add");
            }
        }
        return docId;
    }

    @Override
    public void delete(int idPk) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_DELETE_DOCUMENT);
            ps.setInt(1,  idPk);
            ps.executeUpdate();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException throwables) {
            throw new DAOException("Exception in DocumentDao delete");
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in DocumentDao delete");
            }
        }
    }

    @Override
    public Document get(int idPk) throws DAOException {
        Document document = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_DOCUMENT);
            ps.setString(1, String.valueOf(idPk));
            rs = ps.executeQuery();
            while(rs.next()){
                document = createDocument(rs);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in DocumentDao get");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in DocumentDao get");
            }
        }
        return document;
    }

    private Document createDocument(ResultSet rs) throws DAOException {

        Document document = new Document();
        try {
            document.setPath(rs.getString("path"));
            document.setDescription(rs.getString("description"));
            document.setCat(new Cat(rs.getInt("id_cat")));
            document.setDocumentType(DocumentType.getById(rs.getInt("id_document_type")));
        } catch (SQLException e) {
            throw new DAOException("Exception in DocumentDao createDocument");
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
        Statement st = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_DOCUMENT);
            while(rs.next()) {
                documents.add(createDocument(rs));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in DocumentDao getAll");
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in DocumentDao getAll");
            }
        }
        return documents;
    }

    @Override
    public List<Document> getAllFreeCatPhoto() throws DAOException {
        List<Document> documents = new ArrayList<Document>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_FREE_CAT_PHOTO);
            while(rs.next()) {
                documents.add(createDocument(rs));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in DocumentDao getAllFreeCatPhoto");
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in DocumentDao getAllFreeCatPhoto");
            }
        }
        return documents;
    }

    @Override
    public Document getByCatId(int idCat) throws DAOException {
        Document document = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SELECT_DOCUMENT_BY_CAT_ID);
            rs = ps.executeQuery();
            while(rs.next()){
                document = createDocument(rs);
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Connection pool exception");
        } catch (SQLException e) {
            throw new DAOException("Exception in DocumentDao getByCatId");
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                throw new DAOException("Exception in DocumentDao getByCatId");
            }
        }
        return document;
    }
}
