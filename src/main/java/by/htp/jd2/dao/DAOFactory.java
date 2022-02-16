package by.htp.jd2.dao;

import by.htp.jd2.dao.impl.*;

public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final UserDAO userDAO = new UserDAOImpl();
    private final CatDAO catDAO = new CatDAOImpl();
    private final RequestDAO requestDAODAO = new RequestDAOImpl();
    private final DocumentDAO documentDAO = new DocumentDAOImpl();
    private final BreedDAO breedDAO = new BreedDAOImpl();

    private DAOFactory() {}

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public CatDAO getCatDAO() {
        return catDAO;
    }

    public RequestDAO getRequestDAO() {
        return requestDAODAO;
    }

    public DocumentDAO getDocumentDAO() {
        return documentDAO;
    }

    public BreedDAO getBreedDAO() {
        return breedDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

}
