package com.AutoCentral;

import com.AutoCentral.Controller.accountController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

import java.sql.SQLException;

@SpringBootApplication
public class AutoCentralApplication {
    public static double currentBalance;


    public static void main(String[] args) throws SQLException {
        CrudDB.connectDB();
        SpringApplication.run(AutoCentralApplication.class, args);
    }

}
