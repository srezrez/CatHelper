package by.htp.jd2.service.impl;

import by.htp.jd2.dao.*;
import by.htp.jd2.entity.*;
import by.htp.jd2.service.CatService;
import by.htp.jd2.service.ServiceException;

import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
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
    private static final UserDAO userDao = factory.getUserDAO();

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

    @Override
    public CatListViewModel getCatInfo(int idCat) throws ServiceException {
        CatListViewModel catInfo = null;
        try {
            Document document = documentDAO.getByCatId(idCat);
            Cat cat = catDAO.get(document.getCat().getIdPk());
            cat.setBreed(breedDao.get(cat.getBreed().getIdPk()));
            document.setCat(cat);
            catInfo = new CatListViewModel(cat.getIdPk(), cat.getName(), calculateAge(cat.getBirthDate()),
                    cat.getBreed().getTitle(), document.getPath(), cat.getDescription(), cat.getOwner().getIdPk(), cat.getGender());
        } catch (DAOException e) {
            throw new ServiceException("Exception in getCatInfo");
        }
        return catInfo;
    }

    @Override
    public Cat getCat(int idCat) throws ServiceException {
        Cat cat = null;
        try {
            cat = catDAO.get(idCat);
        } catch (DAOException e) {
            throw new ServiceException("Exception in getCat");
        }
        return cat;
    }

    @Override
    public List<CatListViewModel> getAllFreeFilteredCats(List<Breed> breedList, List<Gender> genderList) throws ServiceException {
        List<CatListViewModel> filteredCatList;
        try {
            List<Document> docs = documentDAO.getAllFreeCatPhoto();
            List<Document> filteredDocs = new ArrayList<>();
            for (Document doc: docs) {
                Cat cat = catDAO.get(doc.getCat().getIdPk());
                cat.setBreed(breedDao.get(cat.getBreed().getIdPk()));
                doc.setCat(cat);
                if(breedList.stream().anyMatch(x -> x.getIdPk() == cat.getBreed().getIdPk())
                        && genderList.stream().anyMatch( y -> y.getIdPk() == cat.getGender().getIdPk())) {
                    filteredDocs.add(doc);
                }
            }
            filteredCatList = this.createCatListfromDoc(filteredDocs);
        } catch (DAOException e) {
            throw new ServiceException("Exception in getAllFreeFilteredCats");
        }
        return filteredCatList;
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
            catListObj.setGender(x.getCat().getGender());
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
            catListObj.setGender(x.getGender());
            return catListObj;
        }).collect(Collectors.toList());
        return catList;
    }

    private String calculateAge(Date date) {
        int years = Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), java.time.LocalDate.now()).getYears();
        if (years == 0) {
            int months = Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), java.time.LocalDate.now()).getMonths();
            return months + " (месяцев)";
        }
        return years + " (лет)";
    }
}
