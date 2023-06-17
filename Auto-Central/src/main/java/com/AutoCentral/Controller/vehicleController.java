package com.AutoCentral.Controller;

import com.AutoCentral.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class vehicleController {
    @PostMapping("/vehicle/insert")
    public static Vehicle insertVehicle(int vehicleID, @RequestParam String name, @RequestParam double price,
                                        @RequestParam Transmission transmission,
                                        @RequestParam FuelType fuel, @RequestParam VehicleType type,
                                        @RequestParam Brand brand){
        Vehicle newVehicle = new Vehicle(vehicleID, name, new Price(price), transmission, fuel, type, brand);
        return newVehicle;
    }

    @GetMapping("/vehicle/show")
    public static void getAllVehicle() throws SQLException {
        ResultSet vehicleList = CrudDB.getAllVehicle();
        while(vehicleList.next()){
            System.out.println(vehicleList.getString("brand") + " " + vehicleList.getString("name"));
        }
    }

    @GetMapping("/vehicle/getById")
    public static Vehicle getById(@RequestParam int id) throws SQLException {
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
