package by.htp.jd2.entity;

import java.util.Objects;

public enum Activity {

    ACTIVE (1, "Действующий"),
    BLOCKED (2, "Заблокированный");

    private int idPk;
    private String title;

    Activity(int idPk, String title) {
        this.idPk = idPk;
        this.title = title;
    }

    public int getIdPk() {
        return idPk;
    }

    public String getTitle() {
        return title;
    }

    public static Activity getById(int idPk) {
        for(Activity act : values()) {
            if(act.idPk == idPk) return act;
        }
        return null;
    }
}
