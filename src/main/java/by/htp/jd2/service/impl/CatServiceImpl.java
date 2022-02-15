package by.htp.jd2.service.impl;

import by.htp.jd2.dao.*;
import by.htp.jd2.entity.Breed;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.entity.Document;
import by.htp.jd2.service.CatService;
import by.htp.jd2.service.ServiceException;

import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {

    private static final DAOFactory factory = DAOFactory.getInstance();
    private static final CatDAO catDAO = factory.getCatDAO();
    private static final DocumentDAO documentDAO = factory.getDocumentDAO();
    private static final BreedDAO breedDao = factory.getBreedDAO();

    @Override
    public void addCat(Cat cat) {
        try {
            catDAO.add(cat);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CatListViewModel> getAllFreeCats() throws ServiceException {
        List<CatListViewModel> catList;
        try {
            List<Document> docs = documentDAO.getAllFreeCatPhoto();
            for (Document doc: docs) {
                Cat cat = catDAO.get(doc.getCat().getIdPk());
                cat.setBreed(breedDao.get(cat.getBreed().getIdPk()));
                doc.setCat(cat);
            }
            catList = createCatList(docs);
        } catch (DAOException e) {
            throw new ServiceException("Exception in getAllFreeCats");
        }
        return catList;
    }

    private List<CatListViewModel> createCatList (List<Document> docs) {
        List<CatListViewModel> catList;
        catList = docs.stream().map(x -> {
            CatListViewModel catListObj = new CatListViewModel();
            catListObj.setIdPk(x.getCat().getIdPk());
            catListObj.setName(x.getCat().getName());
            catListObj.setBreed(x.getCat().getBreed().getTitle());
            catListObj.setAge(calculateAge(x.getCat().getBirthDate()));
            catListObj.setPhotoPath(x.getPath());
            return catListObj;
        }).collect(Collectors.toList());
        return catList;
    }

    private int calculateAge(Date date) {
        return Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), java.time.LocalDate.now()).getYears();
    }
}
