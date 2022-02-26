package by.htp.jd2.service;

import by.htp.jd2.service.impl.BreedServiceImpl;
import by.htp.jd2.service.impl.CatServiceImpl;
import by.htp.jd2.service.impl.RequestServiceImpl;
import by.htp.jd2.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final CatService catService = new CatServiceImpl();
    private final BreedService breedService = new BreedServiceImpl();
    private final RequestService requestService = new RequestServiceImpl();

    public CatService getCatService() {
        return catService;
    }

    public BreedService getBreedService() {
        return breedService;
    }

    private ServiceFactory() {}

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public RequestService getRequestService() {
        return requestService;
    }
}
