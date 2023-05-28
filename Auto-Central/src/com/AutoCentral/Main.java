package com.AutoCentral;

import com.AutoCentral.Controller.accountController;

import java.util.Date;
import java.util.ArrayList;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) {
        CrudDB.connectDB();
        accountController.createAccount("Alif", "alif5623@yahoo.com", "alif5623", AccountType.BUYER);
    }




}
