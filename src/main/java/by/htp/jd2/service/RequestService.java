package by.htp.jd2.service;

import by.htp.jd2.entity.Request;

public interface RequestService {
    void sendRequest(Request request) throws ServiceException;
}
