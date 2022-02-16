package by.htp.jd2.dao;

import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.entity.Request;

import java.util.List;

public interface RequestDAO extends BaseDAO<Request>{

    int getRequestAmountByCatId (int idCat) throws DAOException;
}
