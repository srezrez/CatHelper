package by.htp.jd2.service;

import by.htp.jd2.entity.Breed;

import java.util.List;

public interface BreedService {
    List<Breed> getAll() throws ServiceException;
}
