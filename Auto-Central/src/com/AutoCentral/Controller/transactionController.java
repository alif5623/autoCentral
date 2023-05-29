package com.AutoCentral.Controller;
import com.AutoCentral.*;

import java.sql.SQLException;

import static com.AutoCentral.Main.*;

public class transactionController {
    public static void buy(String email, int vehicleID) throws SQLException {
        Vehicle selectedVehicle = vehicleController.getById(vehicleID);
        String sellerEmail = CrudDB.getSellerByVehicle(selectedVehicle.vehicleID).email;
        System.out.println("Current balance: " + currentBalance);
        if(currentBalance < selectedVehicle.price.price){
            System.out.println("Saldo tidak cukup");
        }else{
            currentBalance -= selectedVehicle.price.price;
            CrudDB.buy(email, vehicleID, currentBalance, selectedVehicle.price.price, sellerEmail);

        }
    }
}

