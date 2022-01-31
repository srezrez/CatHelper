package by.htp.jd2.entity;

import java.util.Date;
import java.util.Objects;

public class User extends AbstractEntity{

    private String name;
    private String surname;
    private Date birthDate;
    private String email;
    private String password;
    private Role role;
    private Activity activity;

    public User() {
    }
    
    public User(int idPk){
        super(idPk);
    }

    public User(String name, String surname, Date birthDate, String email, String password, Role role, Activity activity) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.role = role;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        User user = (User) o;
        return name.equals(user.name) && surname.equals(user.surname) && birthDate.equals(user.birthDate) && email.equals(user.email) && password.equals(user.password) && role.equals(user.role) && activity.equals(user.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, birthDate, email, password, role, activity);
    }
}
