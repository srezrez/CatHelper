package by.htp.jd2.entity;

import java.util.Objects;

public abstract class AbstractEntity {

    private int idPk;

    public AbstractEntity() {
    }

    public AbstractEntity(int idPk) {
        this.idPk = idPk;
    }

    public int getIdPk() {
        return idPk;
    }

    public void setIdPk(int idPk) {
        this.idPk = idPk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return idPk == that.idPk;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPk);
    }
}
