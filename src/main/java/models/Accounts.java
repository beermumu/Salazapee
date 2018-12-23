package models;

public class Accounts {
    private String id ;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String address;
    private String email;
    private String tel;
    private int status;

    public Accounts(String id, String firstname, String lastname, String username, String password, String address, String email, String tel,int status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String  tel) {
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
