package by.htp.jd2.entity;

import java.util.Date;
import java.util.Objects;

public class Request extends AbstractEntity{

    private Date dateRequest;
    private Date dateIssue;
    private User requester;
    private Cat cat;
    private Status status;

    public Request() {
    }

    public Request(Date dateRequest, Date dateIssue, User requester, Cat cat, Status status) {
        this.dateRequest = dateRequest;
        this.dateIssue = dateIssue;
        this.requester = requester;
        this.cat = cat;
        this.status = status;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
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
        if (!(o instanceof Request)) return false;
        if (!super.equals(o)) return false;
        Request request = (Request) o;
        return dateRequest.equals(request.dateRequest) && Objects.equals(dateIssue, request.dateIssue) && requester.equals(request.requester) && cat.equals(request.cat) && status == request.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateRequest, dateIssue, requester, cat, status);
    }
}
