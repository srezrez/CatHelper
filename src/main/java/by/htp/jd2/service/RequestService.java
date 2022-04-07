package by.htp.jd2.service;

import by.htp.jd2.entity.CatRequestViewModel;
import by.htp.jd2.entity.Request;
import by.htp.jd2.entity.RequestViewModel;

import java.util.List;

public interface RequestService {
    void sendRequest(Request request) throws ServiceException;
    List<RequestViewModel> getRequests(int idUser, int idStatus) throws ServiceException;
    void cancelRequest(int idRequest) throws ServiceException;
    CatRequestViewModel getCatRequestInfo (int idCat);
    void approveRequest(int idPk) throws ServiceException;
    void rejectRequest(int idPk) throws ServiceException;
}
