package com.AutoCentral;

import com.AutoCentral.Controller.accountController;
import com.AutoCentral.Controller.vehicleController;
import com.AutoCentral.Controller.transactionController;

import java.util.Date;
import java.util.ArrayList;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Main {
    public static ResultSet currentAccount;
    public static double currentBalance;
    public static void main(String[] args) throws SQLException {
       // accountController.createAccount("Toyota", "toyota@gmail.com", "Toyota123", AccountType.DEALER);
        /*Price price = new Price(200000000);
        CrudDB.insertVehicle(2, "Avanza", VehicleType.CAR, 0, Transmission.AUTOMATIC,
                FuelType.PETROL, price);*/
        accountController.login("kasumi@yahoo.com", "Kasumi123");
        String currentAccount = accountController.getCurrentEmail();
        //accountController.topUp(currentAccount, 1000000000);
        System.out.println(accountController.getCurrentName());
        currentBalance = accountController.getCurrentBalance();
        System.out.println("Current balance: " + currentBalance);
        //vehicleController.getAllVehicle();
       // accountController.topUp(currentAccount, 2000000000);
        transactionController.buy(currentAccount, 0);
       // System.out.println(vehicleController.getById(1).name);
        //System.out.println("Currently login as: \n" + currentAccount.getString("name"));
    }




}
