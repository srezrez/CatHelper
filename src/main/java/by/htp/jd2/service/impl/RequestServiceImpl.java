package by.htp.jd2.service.impl;

import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.RequestDAO;
import by.htp.jd2.entity.Request;
import by.htp.jd2.service.RequestService;
import by.htp.jd2.service.ServiceException;

public class RequestServiceImpl implements RequestService {
    private static final DAOFactory factory = DAOFactory.getInstance();
    private static final RequestDAO requestDao = factory.getRequestDAO();

    @Override
    public void sendRequest(Request request) throws ServiceException {
        try {
            requestDao.add(request);
        } catch (DAOException e) {
            throw new ServiceException("Exception in sendRequest");
        }
    }
}
