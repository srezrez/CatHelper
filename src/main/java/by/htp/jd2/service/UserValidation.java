package by.htp.jd2.service;

import by.htp.jd2.entity.User;
import static by.htp.jd2.util.ConstantPool.*;

public class UserValidation {

    public boolean signInValidation(String email, String password) {
        return (email != null && password != null
                && email.length() > EMAIL_MIN && email.length() < EMAIL_MAX
                && password.length() > PASSWORD_MIN && password.length() < PASSWORD_MAX);
    }

    public boolean signUpValidation(User user) {
        return signInValidation(user.getEmail(), user.getPassword())
                && user.getName() != null && user.getSurname() != null && user.getBirthDate() != null
                && user.getName().length() > NAME_MIN && user.getName().length() < NAME_MAX
                && user.getSurname().length() > NAME_MIN && user.getSurname().length() < NAME_MAX;
    }
}
