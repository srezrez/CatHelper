package by.htp.jd2.service.impl;

import by.htp.jd2.dao.*;
import by.htp.jd2.entity.*;
import by.htp.jd2.service.RequestService;
import by.htp.jd2.service.ServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RequestServiceImpl implements RequestService {
    private static final DAOFactory factory = DAOFactory.getInstance();
    private static final RequestDAO requestDao = factory.getRequestDAO();
    private static final CatDAO catDao = factory.getCatDAO();
    private static final DocumentDAO documentDao = factory.getDocumentDAO();
    private static final UserDAO userDao = factory.getUserDAO();

    @Override
    public void sendRequest(Request request) throws ServiceException {
        try {
            if(requestDao.getActiveRequestByCatAndUser(request.getCat().getIdPk(), request.getRequester().getIdPk()) != null)
                throw new ServiceException("Request already exists");
            requestDao.add(request);
        } catch (DAOException e) {
            throw new ServiceException("Exception in sendRequest");
        }
    }

    @Override
    public List<RequestViewModel> getRequests(int idUser, int idStatus) throws ServiceException {
        List<RequestViewModel> requestList = new ArrayList<>();
        try {
            List<Request> requests = requestDao.getRequestsByUserIdAndStatus(idUser, idStatus);
            for (Request req: requests) {
                req.setCat(catDao.get(req.getCat().getIdPk()));
            }
            requestList = requests.stream().map(x -> {
                RequestViewModel reqListObj = new RequestViewModel();
                reqListObj.setIdPk(x.getIdPk());
                reqListObj.setIdCat(x.getCat().getIdPk());
                reqListObj.setName(x.getCat().getName());
                reqListObj.setDateRequest(x.getDateRequest());
                try {
                    reqListObj.setNumberInQueue(requestDao.getQueueAmount(x.getCat().getIdPk(), x.getDateRequest()));
                } catch (DAOException e) {
                    e.printStackTrace();
                }
                reqListObj.setStatus(x.getStatus());
                return reqListObj;
            }).collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException("Exception in getRequests");
        }
        return requestList;
    }

    @Override
    public void cancelRequest(int idRequest) throws ServiceException {
        try {
            requestDao.cancelRequest(idRequest);
        } catch (DAOException e) {
            throw new ServiceException("Exception in cancelRequest");
        }
    }

    @Override
    public CatRequestViewModel getCatRequestInfo(int idCat) {
        CatRequestViewModel catRequest = new CatRequestViewModel();
        try {
            catRequest.setRequestAmount(requestDao.getRequestAmountByCatId(idCat));
            Request request = requestDao.getFirstActiveRequestByCat(idCat);
            catRequest.setRequester(userDao.get(request.getRequester().getIdPk()));
            catRequest.setIdPk(request.getIdPk());
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return catRequest;
    }

    @Override
    public void approveRequest(int idPk) throws ServiceException {
        try {
            Request request = requestDao.get(idPk);
            request.setStatus(Status.REQUEST_ACCEPTED);
            request.setDateIssue(new Date());
            requestDao.update(request);
        } catch (DAOException e) {
            throw new ServiceException("Exception in approveRequest");
        }

    }

    @Override
    public void rejectRequest(int idPk) throws ServiceException {
        try {
            Request request = requestDao.get(idPk);
            request.setStatus(Status.REQUEST_DENIED);
            request.setDateIssue(new Date());
            requestDao.update(request);
        } catch (DAOException e) {
            throw new ServiceException("Exception in approveRequest");
        }
    }
}
