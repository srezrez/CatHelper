package by.htp.jd2.entity;

import java.util.Objects;

public enum Role {

    ADMIN (1, "Администратор"),
    USER (2, "Пользователь");

    private int idPk;
    private String title;

    Role(int idPk, String title) {
        this.idPk = idPk;
        this.title = title;
    }

    public int getIdPk() {
        return idPk;
    }

    public String getTitle() {
        return title;
    }

    public static Role getById(int idPk) {
        for(Role role : values()) {
            if(role.idPk == idPk) return role;
        }
        return null;
    }
}
