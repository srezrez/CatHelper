package by.htp.jd2.dao;

import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.entity.Request;

import java.util.Date;
import java.util.List;

public interface RequestDAO extends BaseDAO<Request>{

    int getRequestAmountByCatId (int idCat) throws DAOException;
    List<Request> getRequestsByUserIdAndStatus(int idUser, int idStatus) throws DAOException;
    int getQueueAmount (int idCat, Date requestDate) throws DAOException;
    void cancelRequest (int idRequest) throws DAOException;
    int getRequestQueueAmount (int idCat) throws DAOException;
    Request getFirstActiveRequestByCat (int idCat) throws DAOException;
}
