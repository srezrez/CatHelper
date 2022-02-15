package by.htp.jd2.service;

import by.htp.jd2.service.impl.CatServiceImpl;
import by.htp.jd2.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final CatService catService = new CatServiceImpl();

    public CatService getCatService() {
        return catService;
    }

    private ServiceFactory() {}

    public UserService getUserService() {
        return userService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
