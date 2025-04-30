package com.pluralsight.util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Ledger {
    static  String path = "";
    public static void setPath(){
        path = Files.exists(Path.of("src/main/resources")) ? "src/main/resources/transactions.csv" : "transactions.csv";
    }
//USED BY HOME MENU
    public static String addDeposit(Float amount, String description, String vendor){
        if(amount == null || amount < 0 || description == null || vendor == null) return "Transaction Cancelled";
        return String.format("%s", FileManager.write(new Transaction(amount, description, vendor, false), path));
    }
    public static String makePayment(Float amount, String description, String vendor) {
        if(amount == null || description == null || vendor == null) return "Transaction Cancelled";
        return String.format("%s", FileManager.write(new Transaction(amount, description, vendor, true), path));
    }
    public static String logout(){
        return "Logged Out";
    }

//USED BY LEDGER MENU
    public static String getAll(){
        AtomicReference<String> res = new AtomicReference<>("Transactions Found:");
        try {
            FileManager.read(path).forEach(transaction -> res.set(String.format("%s\n%s", res.get(),transaction)));
        } catch (IOException e) {
            return "FILE COULD NOT BE READ";
        }
        return res.get();
    }
    public static String getDeposits(){
        AtomicReference<String> res = new AtomicReference<>("Transactions Found:");
        try {
            FileManager.read(path).stream().filter(transaction -> !transaction.isPayment).forEach(transaction -> res.set(String.format("%s\n%s", res.get(),transaction)));
        } catch (IOException e) {
            return "FILE COULD NOT BE READ";
        }
        return res.get();
    }
    public static String getPayments(){
        AtomicReference<String> res = new AtomicReference<>("Transactions Found:");
        try {
            FileManager.read(path).stream().filter(transaction -> transaction.isPayment).forEach(transaction -> res.set(String.format("%s\n%s", res.get(),transaction)));

        } catch (IOException e) {
            return "FILE COULD NOT BE READ";
        }
        return res.get();
    }


//USED BY REPORTS MENU
    //MONTH TO DATE OPTION
    public static String monthToDate(){
        //MONTH TO DATE LOGIC HERE
        Month month = LocalDate.now().getMonth();

        AtomicReference<String> res = new AtomicReference<>("Transactions Found:");
        try {
            FileManager.read(path).stream().filter(transaction -> transaction.date.getMonth().equals(month)).forEach(transaction -> res.set(String.format("%s\n%s", res.get(),transaction)));
        } catch (IOException e) {
            return "FILE COULD NOT BE READ";
        }
        return res.get();
    }

    //PREVIOUS MONTH OPTION
    public static String previousMonth(){
        Month month = LocalDate.now().getMonth().minus(1);
        AtomicReference<String> res = new AtomicReference<>("Transactions Found:");
        try {
            FileManager.read(path).stream().filter(transaction -> transaction.date.getMonth().equals(month)).forEach(transaction -> res.set(String.format("%s\n%s", res.get(),transaction)));
        } catch (IOException e) {
            return "FILE COULD NOT BE READ";
        }
        return res.get();
    }

    //YEAR TO DATE OPTION
    public static String yearToDate(){
        int year = LocalDate.now().getYear();
        AtomicReference<String> res = new AtomicReference<>("Transactions Found:");
        try {
            FileManager.read(path).stream().filter(transaction -> transaction.date.getYear() == year).forEach(transaction -> res.set(String.format("%s\n%s", res.get(),transaction)));
        } catch (IOException e) {
            return "FILE COULD NOT BE READ";
        }
        return res.get();
    }

    //PREVIOUS YEAR OPTION
    public static String previousYear() {
        int year = LocalDate.now().getYear() - 1;
        AtomicReference<String> res = new AtomicReference<>("Transactions Found:");
        try {
            FileManager.read(path).stream().filter(transaction -> transaction.date.getYear() == year).forEach(transaction -> res.set(String.format("%s\n%s", res.get(),transaction)));
        } catch (IOException e) {
            return "FILE COULD NOT BE READ";
        }
        return res.get();
    }

    //SEARCH BY VENDOR OPTION
    public static String searchByVendor(String vendorName){
        AtomicReference<String> res = new AtomicReference<>("Transactions Found:");
        try {
            FileManager.read(path).stream().filter(transaction -> transaction.vendor.equalsIgnoreCase(vendorName)).forEach(transaction -> res.set(String.format("%s\n%s", res.get(),transaction)));
        } catch (IOException e) {
            return "FILE COULD NOT BE READ";
        }
        return res.get();
    }

    //SEARCH BY ALL OPTION
    public static String searchByAll(LocalDate startDate, LocalDate endDate, String desc, String vendorName, Float minPrice, Float maxPrice){
        try {
            Stream<Transaction> transactions =  FileManager.read(path).stream();
            if(startDate != null) transactions = transactions.filter(transaction -> transaction.date.isAfter(startDate));
            if(endDate != null) transactions = transactions.filter(transaction -> transaction.date.isBefore(endDate));
            if(desc != null && !desc.isBlank()) transactions = transactions.filter(transaction -> transaction.description.equalsIgnoreCase(desc));
            if(vendorName != null && !vendorName.isBlank()) transactions = transactions.filter(transaction -> transaction.vendor.equalsIgnoreCase(vendorName));
            if(minPrice != null && minPrice > 0) transactions = transactions.filter(transaction -> transaction.amount > minPrice);
            if(maxPrice != null && maxPrice > 0) transactions = transactions.filter(transaction -> transaction.amount < maxPrice);
            AtomicReference<String> res = new AtomicReference<>("Transactions Found:");
            transactions.forEach(transaction -> res.set(String.format("%s\n%s", res.get(),transaction)));
            return res.get();
        } catch (IOException e) {
            return "FILE COULD NOT BE READ";
        }
    }
}
