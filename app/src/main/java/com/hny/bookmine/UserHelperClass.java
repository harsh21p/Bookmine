package com.hny.bookmine;

public class UserHelperClass {
    String username, name, password, email, number;

    public UserHelperClass(String username, String name, String password, String email, String number) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.number = number;
    }

    public UserHelperClass() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

