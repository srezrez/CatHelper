package by.htp.jd2.dao;

import by.htp.jd2.entity.Document;

import java.util.List;

public interface DocumentDAO extends BaseDAO<Document>{
    List<Document> getAllFreeCatPhoto() throws DAOException;
    Document getByCatId(int idCat) throws DAOException;
}
