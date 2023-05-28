package com.AutoCentral.Controller;
import com.AutoCentral.AccountType;
import com.AutoCentral.Account;
import com.AutoCentral.CrudDB;

public class accountController {
    public accountController(){

    }
    public static Account createAccount(String name, String email, String password, AccountType type){
        Account newAccount = new Account(name, email, password, type);
        CrudDB.insertAccount(name, email, password, newAccount.balance, type);
        return newAccount;
    }
}
