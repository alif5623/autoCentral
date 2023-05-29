package com.AutoCentral.Controller;

import com.AutoCentral.*;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class vehicleController {
    public static Vehicle insertVehicle(int vehicleID, String name, VehicleType type){
        return null;
    }
    public static void getAllVehicle() throws SQLException {
        ResultSet vehicleList = CrudDB.getAllVehicle();
        while(vehicleList.next()){
            System.out.println(vehicleList.getString("brand") + " " + vehicleList.getString("name"));
        }
    }

    public static Vehicle getById(int id) throws SQLException {
        ResultSet vehicle = CrudDB.Vehicle(id);
        while(vehicle.next()){
            Vehicle selectedVehicle = new Vehicle(id, vehicle.getString("name"), new Price(vehicle.getDouble("price")),
                    Transmission.valueOf(vehicle.getString("transmission")), FuelType.valueOf(vehicle.getString("fuel")),
                    VehicleType.valueOf(vehicle.getString("type")), Brand.valueOf(vehicle.getString("brand").toUpperCase()));
            return selectedVehicle;
        }
        return null;
    }

}
