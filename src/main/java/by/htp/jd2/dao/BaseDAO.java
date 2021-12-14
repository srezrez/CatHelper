package by.htp.jd2.dao;

import by.htp.jd2.entity.AbstractEntity;

public interface BaseDAO<T extends AbstractEntity> {

    void add(T entity);
    void delete(int idPk);
    T get(int idPk);
    void update(T entity);

}
