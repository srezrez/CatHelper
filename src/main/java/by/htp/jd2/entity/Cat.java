package by.htp.jd2.entity;

import java.util.Date;
import java.util.Objects;

public class Cat extends AbstractEntity{

    private String name;
    private Date birthDate;
    private User owner;
    private Breed breed;
    private Gender gender;

    public Cat() {
    }

    public Cat(int idPk){
        super(idPk);
    }

    public Cat(String name, Date birthDate, User owner, Breed breed, Gender gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
        this.breed = breed;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name) && Objects.equals(birthDate, cat.birthDate) && Objects.equals(owner, cat.owner) && Objects.equals(breed, cat.breed) && gender == cat.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, birthDate, owner, breed, gender);
    }
}
