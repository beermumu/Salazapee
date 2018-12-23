package Models;

public class Invoice {
    private String id;
    private String date;
    private int status;
    private String accountID;

    public Invoice(String id, String date, int status, String accountID) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.accountID = accountID;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public String getAccountID() {
        return accountID;
    }
}
