package com.example.de.domain;

//import com.sun.istack.NotNull;
//import com.sun.istack.NotNull;

public class UserLogin {
    //@NotNull
    //@Size(min=6)
    public String account;
    public String password;

    //@NotNull
    //@Size(min=6)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
