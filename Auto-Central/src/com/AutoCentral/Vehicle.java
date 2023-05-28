package com.AutoCentral;

public class Vehicle {
    public String name;
    public Price price;
    public Brand brand;
    public Transmission transmission;
    public FuelType fuelType;
    public VehicleType type;
    public Vehicle(String name, Price price, Transmission transmission , FuelType fuelType, VehicleType type,
                   Brand brand){
        this.name = name;
        this.price = price;
        this.transmission = transmission;
        this.fuelType = fuelType;
        this.type = type;
        this.brand = brand;
    }
}
