package com.AutoCentral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CrudDB {
    public static Connection c = null;
    public static void connectDB(){
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/autocentral",
                            "postgres", "123");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void insertAccount(String name, String email, String password, double balance, AccountType type){
        connectDB();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO account VALUES('" + name + "', '" + email +"', '" + password
                    + "', " + 0 + ", '" + type + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public static void insertSeller(String email, String imageURL){
        connectDB();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO seller (email, img_url) VALUES('" + email + "', '" + imageURL + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public static void insertVehicle(int vehicleID, String name, VehicleType type, int SellerID ,
                                     Transmission transmission, FuelType fuel, Price price){
        connectDB();
        Statement stmt = null;
        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO vehicle VALUES(" + vehicleID + ", '" + name + "', '" + type + "', " + SellerID + ");";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            sql = "INSERT INTO vehicleDetail VALUES(" + vehicleID + ", '" + transmission + "', '" +
                    fuel + "', " + price.price +");";
            System.out.println(sql);
            stmt.execute(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    public static ResultSet login(String email, String passsword){
        connectDB();
        Statement stmt = null;
        try{
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM account WHERE email='" + email + "' AND password='" + passsword +"';";
            ResultSet selectedAccount= stmt.executeQuery(sql);
            c.commit();
            c.close();
            return selectedAccount;
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return null;
    }
    public static void topUp(String email, double balanceTopup){
        connectDB();
        Statement stmt = null;
        try{
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "UPDATE account SET balance = balance + " + balanceTopup +
                    " WHERE email = '" + email + "';";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            c.commit();
            c.close();
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    public static ResultSet getAllVehicle(){
        connectDB();
        Statement stmt = null;
        try{
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM vehicle NATURAL JOIN vehicleDetail";
            ResultSet vehicleList = stmt.executeQuery(sql);
            c.commit();
            c.close();
            return vehicleList;
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return null;
    }

    public static void buy(String email, int vehicleID, double balance, double price, String sellerEmail){
        connectDB();
        Statement stmt = null;
        try{
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "UPDATE account SET balance = balance - " + price + " WHERE email = '" +
                    email + "';";
            stmt.executeUpdate(sql);
            sql = "UPDATE account SET balance = balance + " + price + " WHERE email = '" +
                    sellerEmail + "';";
            stmt.executeUpdate(sql);
            c.commit();
            c.close();
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

    }

    public static ResultSet Vehicle(int id){
        connectDB();
        Statement stmt = null;
        try{
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM vehicle NATURAL JOIN vehicleDetail WHERE vehicleID = " + id + ";";
            ResultSet vehicle = stmt.executeQuery(sql);
            c.commit();
            c.close();
            return vehicle;
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return null;
    }

    public static Account getSellerByVehicle(int vehicleID){
        connectDB();
        Statement stmt = null;
        try{
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM account JOIN vehicleDetail ON sellerid = sellerid " +
                    "WHERE vehicleid = " + vehicleID + ";";
            ResultSet seller = stmt.executeQuery(sql);
            while(seller.next()){
                Account sellerAccount = new Account(seller.getString("name"), seller.getString("email"),
                        seller.getString("password"), AccountType.valueOf(seller.getString("type")));
                return sellerAccount;
            }
            c.commit();
            c.close();
            return null;
        }catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return null;
    }
}
