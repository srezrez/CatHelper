package by.htp.jd2.service.impl;

import by.htp.jd2.dao.BreedDAO;
import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.entity.Breed;
import by.htp.jd2.service.BreedService;
import by.htp.jd2.service.ServiceException;

import java.util.List;

public class BreedServiceImpl implements BreedService {

    private static final DAOFactory factory = DAOFactory.getInstance();
    private static final BreedDAO breedDao = factory.getBreedDAO();
    @Override
    public List<Breed> getAll() throws ServiceException {
        List<Breed> breeds;
        try {
            breeds = breedDao.getAll();
        } catch (DAOException e) {
            throw new ServiceException("Exception in getAll Breed");
        }
        return breeds;
    }
}
