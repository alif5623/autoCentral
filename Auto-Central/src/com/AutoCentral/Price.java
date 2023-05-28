package com.AutoCentral;

public class Price {
    public double price;
    public double discount;
    public Price(double price){
        this.price = price;
    }
    public Price(double price, double discount){
        this.price = (1-discount) * price;
    }
}
