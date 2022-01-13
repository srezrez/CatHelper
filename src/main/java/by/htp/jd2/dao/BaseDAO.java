package by.htp.jd2.dao;

import by.htp.jd2.entity.AbstractEntity;

public interface BaseDAO<T extends AbstractEntity> {

    void add(T entity) throws DAOException;
    void delete(int idPk);
    T get(int idPk) throws DAOException;
    void update(T entity);

}
