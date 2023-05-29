package com.AutoCentral;

public class Vehicle {
    public int vehicleID;
    public String name;
    public Price price;
    public Brand brand;
    public Transmission transmission;
    public FuelType fuelType;
    public VehicleType type;
    public Vehicle(int vehicleID, String name, Price price, Transmission transmission , FuelType fuelType, VehicleType type,
                   Brand brand){
        this.vehicleID = vehicleID;
        this.name = name;
        this.price = price;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.type = type;
        this.brand = brand;
    }
}
