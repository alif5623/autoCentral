package com.AutoCentral;

public class Account {
    public String name;
    public String email;
    public String password;
    public double balance;
    public AccountType type;
    public Account(String name, String email, String password, AccountType type){
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.type = type;
    }
}
