package com.AutoCentral.Controller;

import com.AutoCentral.Account;
import com.AutoCentral.AccountType;
import com.AutoCentral.CrudDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

@RestController
public class accountController {
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+([.]?[a-zA-Z]+)*\\.[a-zA-Z]+(?!\\s)$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[a-zA-Z\\d]{8,}(?!\\s)$";
    public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile(REGEX_PASSWORD);
    public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile(REGEX_EMAIL);
    private static String currentEmail;
    private static String currentName;
    private static double currentBalance;
    private static AccountType currentType;
    public accountController(){

    }
    @PostMapping("/account/create")
    public static Account createAccount(@RequestParam String name, @RequestParam String email,
                                        @RequestParam String password, @RequestParam AccountType type,
                                        @RequestParam String imageURL){
        if(!name.isBlank()) {
            if (REGEX_PATTERN_EMAIL.matcher(email).matches() && REGEX_PATTERN_PASSWORD.matcher(password).matches()) {
                String generatedPassword = null;
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] bytes = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for (byte aByte : bytes) {
                        sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
                    }
                    generatedPassword = sb.toString();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                Account newAccount = new Account(name, email, generatedPassword, type);
                if(newAccount.type == AccountType.DEALER || newAccount.type == AccountType.SELLER)
                    CrudDB.insertSeller(email, imageURL);
                CrudDB.insertAccount(name, email, generatedPassword, newAccount.balance, type);
                return newAccount;
            }
        }
        return null;
    }
    @PostMapping("/account/login")
    public static Account login(@RequestParam String email, @RequestParam String password) throws SQLException {
        System.out.println(email);
        System.out.println(password);
        String generatedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            ResultSet loggedAccount = CrudDB.login(email, generatedPassword);
            while(loggedAccount.next()){
                currentEmail = loggedAccount.getString("email");
                currentName = loggedAccount.getString("name");
                currentBalance = loggedAccount.getDouble("balance");
                currentType = AccountType.valueOf(loggedAccount.getString("type"));
            }
            Account loggedAcc = new Account(currentName, currentEmail, password, currentType);
            return loggedAcc;
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping("/account/topUp")
    public static void topUp(double balance){
        System.out.println(currentEmail);
        String email = getCurrentEmail();
        CrudDB.topUp(email, balance);
        System.out.println("Top up success");
    }

    public static String getCurrentEmail(){
        return currentEmail;
    }
    public static String getCurrentName(){
        return currentName;
    }
    public static double getCurrentBalance(){
        return currentBalance;
    }
}
