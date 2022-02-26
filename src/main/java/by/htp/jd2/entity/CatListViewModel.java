package by.htp.jd2.entity;

import java.util.Objects;

public class CatListViewModel extends AbstractEntity{

    private String name;
    private int age;
    private String breed;
    private String photoPath;
    private String description;
    private int requestAmount;

    public CatListViewModel() {
    }

    public CatListViewModel(int idPk, String name, int age, String breed, String photoPath, String description) {
        super(idPk);
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.photoPath = photoPath;
        this.description = description;
    }

    public CatListViewModel(int idPk) {
        super(idPk);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(int requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        if (!super.equals(o)) return false;
        CatListViewModel that = (CatListViewModel) o;
        return age == that.age && name.equals(that.name) && breed.equals(that.breed) && photoPath.equals(that.photoPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, age, breed, photoPath);
    }
}
