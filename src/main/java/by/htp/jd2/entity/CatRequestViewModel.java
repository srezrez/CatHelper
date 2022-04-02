package by.htp.jd2.entity;

public class CatRequestViewModel extends AbstractEntity{
    private int requestAmount;
    private User requester;

    public CatRequestViewModel() {
    }

    public CatRequestViewModel(int requestAmount, User requester) {
        this.requestAmount = requestAmount;
        this.requester = requester;
    }

    public CatRequestViewModel(int idPk, int requestAmount, User requester) {
        super(idPk);
        this.requestAmount = requestAmount;
        this.requester = requester;
    }

    public int getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(int requestAmount) {
        this.requestAmount = requestAmount;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }
}
