package by.htp.jd2.service;

import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.CatListViewModel;

import java.util.List;

public interface CatService {
    void addCat(Cat cat);
    List<CatListViewModel> getAllFreeCats() throws ServiceException;
}
