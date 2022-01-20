package by.htp.jd2.entity;

public enum Gender {

    MALE (1, "Мужской"),
    FEMALE (2, "Женский");

    private int idPk;
    private String title;

    Gender(int idPk, String title) {
        this.idPk = idPk;
        this.title = title;
    }

    public int getIdPk() {
        return idPk;
    }

    public String getTitle() {
        return title;
    }

    public static Gender getById(int idPk) {
        for(Gender gender : values()) {
            if(gender.idPk == idPk) return gender;
        }
        return null;
    }
}
