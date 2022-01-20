package by.htp.jd2.entity;

import java.util.Objects;

public class Document extends AbstractEntity{

    private String path;
    private String description;
    private Cat cat;
    private DocumentType documentType;

    public Document() {
    }

    public Document(String path, String description, Cat cat, DocumentType documentType) {
        this.path = path;
        this.description = description;
        this.cat = cat;
        this.documentType = documentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        if (!super.equals(o)) return false;
        Document document = (Document) o;
        return path.equals(document.path) && description.equals(document.description) && cat.equals(document.cat) && documentType == document.documentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), path, description, cat, documentType);
    }
}
