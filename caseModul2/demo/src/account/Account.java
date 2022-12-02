package account;

import java.io.Serializable;

public class Account implements Serializable {
    private String username;
    private String password;

    private String mkcap2;

    private int sdt ;

    private String email;

    public Account(String username, String password, String mkcap2, int sdt, String email) {
        this.username = username;
        this.password = password;
        this.mkcap2 = mkcap2;
        this.sdt = sdt;
        this.email = email;
    }

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }





    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getMkcap2() {
        return mkcap2;
    }

    public void setMkcap2(String mkcap2) {
        this.mkcap2 = mkcap2;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}