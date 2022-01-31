package by.htp.jd2.entity;

import java.util.Objects;

public class Breed extends AbstractEntity{

    private String title;

    public Breed() {
    }
    public Breed(int idPk, String title) {
        super(idPk);
        this.title = title;
    }

    public Breed(int idPk) {
        super(idPk);
    }

    public Breed(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Breed breed = (Breed) o;
        return Objects.equals(title, breed.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }
}
