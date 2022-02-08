package by.htp.jd2.service.impl;

import by.htp.jd2.dao.CatDAO;
import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.service.CatService;

public class CatServiceImpl implements CatService {
    @Override
    public void addCat(Cat cat) {
        DAOFactory factory = DAOFactory.getInstance();
        CatDAO catDAO = factory.getCatDAO();
        try {
            catDAO.add(cat);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
