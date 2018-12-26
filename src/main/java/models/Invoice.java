package models;

public class Invoice {
    private String details;
    private String date;
    private String accountID;

    public Invoice(String details, String date, String accountID) {
        this.details = details;
        this.date = date;
        this.accountID = accountID;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }

    public String getAccountID() {
        return accountID;
    }
}
