package by.htp.jd2.entity;

import java.util.Date;
import java.util.Objects;

public class RequestViewModel extends AbstractEntity{

    private int idCat;
    private String name;
    private Date dateRequest;
    private int numberInQueue;
    private Status status;

    public RequestViewModel() {
    }

    public RequestViewModel(int idCat, String name, Date dateRequest, int numberInQueue, Status status) {
        this.idCat = idCat;
        this.name = name;
        this.dateRequest = dateRequest;
        this.numberInQueue = numberInQueue;
        this.status = status;
    }

    public RequestViewModel(int idPk, int idCat, String name, Date dateRequest, int numberInQueue, Status status) {
        super(idPk);
        this.idCat = idCat;
        this.name = name;
        this.dateRequest = dateRequest;
        this.numberInQueue = numberInQueue;
        this.status = status;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public int getNumberInQueue() {
        return numberInQueue;
    }

    public void setNumberInQueue(int numberInQueue) {
        this.numberInQueue = numberInQueue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        RequestViewModel that = (RequestViewModel) o;
        return idCat == that.idCat && numberInQueue == that.numberInQueue && name.equals(that.name) && dateRequest.equals(that.dateRequest) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idCat, name, dateRequest, numberInQueue, status);
    }
}
