package by.htp.jd2.service.impl;

import by.htp.jd2.dao.*;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.entity.Document;
import by.htp.jd2.entity.DocumentType;
import by.htp.jd2.service.CatService;
import by.htp.jd2.service.ServiceException;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static by.htp.jd2.util.ConstantPool.*;

public class CatServiceImpl implements CatService {

    private static final DAOFactory factory = DAOFactory.getInstance();
    private static final CatDAO catDAO = factory.getCatDAO();
    private static final DocumentDAO documentDAO = factory.getDocumentDAO();
    private static final BreedDAO breedDao = factory.getBreedDAO();
    private static final RequestDAO requestDao = factory.getRequestDAO();

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
            catList = this.createCatListfromDoc(docs);
        } catch (DAOException e) {
            throw new ServiceException("Exception in getAllFreeCats");
        }
        return catList;
    }

    @Override
    public List<CatListViewModel> getAddedCats(int idUser) throws ServiceException {
        List<CatListViewModel> catList;
        try {
            List<Cat> cats = catDAO.getAllAddedActiveCats(idUser);
            for (Cat cat: cats) {
                cat.setBreed(breedDao.get(cat.getBreed().getIdPk()));
            }
            catList = createCatListfromCat(cats);
            for (CatListViewModel cat: catList) {
                cat.setRequestAmount(requestDao.getRequestAmountByCatId(cat.getIdPk()));
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception in getAddedCats");
        }
        return catList;
    }

    @Override
    public void addCat(Cat cat, String fileName) throws ServiceException {
        try {
            int idCat = catDAO.add(cat);
            Document doc = new Document(PHOTO_PATH_DB + fileName, "Описание", new Cat(idCat), DocumentType.PHOTO);
            documentDAO.add(doc);
        } catch (DAOException e) {
            throw new ServiceException("Exception in addCat");
        }
    }

    private List<CatListViewModel> createCatListfromDoc(List<Document> docs) {
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

    private List<CatListViewModel> createCatListfromCat(List<Cat> cats) {
        List<CatListViewModel> catList;
        catList = cats.stream().map(x -> {
            CatListViewModel catListObj = new CatListViewModel();
            catListObj.setIdPk(x.getIdPk());
            catListObj.setName(x.getName());
            catListObj.setBreed(x.getBreed().getTitle());
            catListObj.setAge(calculateAge(x.getBirthDate()));
            return catListObj;
        }).collect(Collectors.toList());
        return catList;
    }

    private int calculateAge(Date date) {
        return Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), java.time.LocalDate.now()).getYears();
    }
}
