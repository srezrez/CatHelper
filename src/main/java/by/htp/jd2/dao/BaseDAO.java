package by.htp.jd2.dao;

import by.htp.jd2.entity.AbstractEntity;

import java.util.List;

public interface BaseDAO<T extends AbstractEntity> {

    void add(T entity) throws DAOException;
    void delete(int idPk) throws DAOException;
    T get(int idPk) throws DAOException;
    void update(T entity) throws DAOException;
    List<T> getAll() throws DAOException;

}
