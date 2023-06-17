package com.AutoCentral.Controller;

import com.AutoCentral.CrudDB;
import com.AutoCentral.Vehicle;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import static com.AutoCentral.AutoCentralApplication.currentBalance;

@RestController
public class transactionController {
    @PostMapping("/transaction/buy")
    public static void buy(@RequestParam String email, @RequestParam int vehicleID) throws SQLException {
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

