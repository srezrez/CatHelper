package by.htp.jd2.dao;

import by.htp.jd2.entity.Cat;

import java.util.List;

public interface CatDAO extends BaseDAO<Cat>{
    public List<Cat> getAllFreeCats() throws DAOException;
}
