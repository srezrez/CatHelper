package by.htp.jd2.service;

import by.htp.jd2.entity.Breed;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.CatListViewModel;
import by.htp.jd2.entity.Gender;

import java.util.List;

public interface CatService {
    List<CatListViewModel> getAllFreeCats() throws ServiceException;
    List<CatListViewModel> getAddedCats(int idUser) throws ServiceException;
    void addCat(Cat cat, String fileName) throws ServiceException;
    CatListViewModel getCatInfo(int idCat) throws ServiceException;
    Cat getCat (int idCat) throws ServiceException;
    List<CatListViewModel> getAllFreeFilteredCats(List<Breed> breedList, List<Gender> genderList) throws ServiceException;
}
