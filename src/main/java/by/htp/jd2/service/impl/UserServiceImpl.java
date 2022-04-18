package by.htp.jd2.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.htp.jd2.dao.DAOException;
import by.htp.jd2.dao.DAOFactory;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.entity.Activity;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.UserValidation;

import java.util.List;

import static by.htp.jd2.util.ConstantPool.PASSWORD_PARAMETER;

public class UserServiceImpl implements UserService {

    private static final UserValidation userValidation = new UserValidation();
    private static final DAOFactory factory = DAOFactory.getInstance();
    private static final UserDAO userDAO = factory.getUserDAO();

    @Override
    public User signIn(String email, String password) throws ServiceException {
        if(!userValidation.signInValidation(email, password))
            throw new ServiceException("User Validation Exception");
        User user = userDAO.getByEmail(email);
        if(user == null || !((BCrypt.verifyer().verify(password.toCharArray(), user.getPassword())).verified)) {
            return null;
        }
        return user;
    }

    @Override
    public boolean signUp(User user) throws ServiceException {
        if(!userValidation.signUpValidation(user))
            throw new ServiceException("User Validation Exception");
        try {
            if(userDAO.getByEmail(user.getEmail()) != null)
                return false;
            user.setPassword(BCrypt.withDefaults().
                    hashToString(12, user.getPassword().toCharArray()));
            userDAO.add(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception in sign up");
        }
        return true;
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException("Exception in getAll");
        }
    }

    @Override
    public void changeActivity(int idUser) throws ServiceException {
        try {
            User user = userDAO.get(idUser);
            user.setActivity(user.getActivity().equals(Activity.ACTIVE) ? Activity.BLOCKED : Activity.ACTIVE);
            userDAO.updateActivity(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception in changeActivity");
        }
    }

    @Override
    public User get(int idUser) {
        User user = null;
        try {
            user = userDAO.get(idUser);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void changePassword(int idUser, String password) throws ServiceException {
        try {
            User user = userDAO.get(idUser);
            user.setPassword(BCrypt.withDefaults().hashToString(12, password.toCharArray()));
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception in changePassword");
        }
    }
}
