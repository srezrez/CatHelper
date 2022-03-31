package by.htp.jd2.service;

import by.htp.jd2.entity.Request;
import by.htp.jd2.entity.RequestViewModel;

import java.util.List;

public interface RequestService {
    void sendRequest(Request request) throws ServiceException;
    List<RequestViewModel> getRequests(int idUser) throws ServiceException;
    void cancelRequest(int idRequest) throws ServiceException;
}
