package by.htp.jd2.entity;

public enum DocumentType {
    PHOTO (1, "Фотография"),
    PAYMENT (2, "Документ об оплате");

    private int idPk;
    private String title;

    DocumentType(int idPk, String title) {
        this.idPk = idPk;
        this.title = title;
    }

    public int getIdPk() {
        return idPk;
    }

    public String getTitle() {
        return title;
    }

    public static DocumentType getById(int idPk) {
        for(DocumentType documentType : values()) {
            if(documentType.idPk == idPk) return documentType;
        }
        return null;
    }
}
