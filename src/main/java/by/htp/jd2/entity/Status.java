package by.htp.jd2.entity;

public enum Status {

    REQUEST_CREATED (1, "Заявка создана"),
    REQUEST_ACCEPTED (2, "Заявка одобрена"),
    REQUEST_DENIED (3, "Заявка отклонена");

    private int idPk;
    private String title;

    Status(int idPk, String title) {
        this.idPk = idPk;
        this.title = title;
    }

    public int getIdPk() {
        return idPk;
    }

    public String getTitle() {
        return title;
    }

    public static Status getById(int idPk) {
        for(Status status : values()) {
            if(status.idPk == idPk) return status;
        }
        return null;
    }
}
