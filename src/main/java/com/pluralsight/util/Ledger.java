package com.pluralsight.util;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ledger {
    static  String path = "";
    public static void setPath(){
        path = Files.exists(Path.of("src/main/resources")) ? "src/main/resources/transactions.csv" : "./data/transactions.csv";
    }
//USED BY HOME MENU
    public static String addDeposit(Float amount, String description, String vendor){
        if(amount == null || amount < 0 || description == null || vendor == null) return "\033[1;33mTransaction Cancelled\033[0m";;

        return String.format("%s", FileManager.write(new Transaction(amount, description, vendor, false), path));
    }

    public static String makePayment(Float amount, String description, String vendor) {
        if(amount == null || amount < 0 || description == null || vendor == null) return "\033[1;33mTransaction Cancelled\033[0m";;

        return String.format("%s", FileManager.write(new Transaction(amount, description, vendor, true), path));
    }

    public static String logout(){
        return "Logged Out";
    }

//USED BY LEDGER MENU
    public static String getAll(){
        StringBuilder res = new StringBuilder("\nAll Available Transactions:\nDate|Time|Description|Vendor|Amount\n");
        res.append("---------------------------------------------------").append("\n");

        float accountBalance = 0;
        float totalDeposits = 0;
        float totalPayments = 0;

        ArrayList<Transaction> transactions = FileManager.read(path);
        if(transactions == null) return "Unable to Load Transactions";

        for(Transaction transaction: transactions){
            if(transaction.isPayment) totalPayments += transaction.amount; else totalDeposits += transaction.amount;
            res.append(transaction.toPrettyString()).append("\n");
            accountBalance += transaction.amount;
        }

        res.append("---------------------------------------------------\nSummary: ").append("\n\033[0m");
        res.append("\t\033[32mDeposit Balance: ").append(totalDeposits).append("\n\033[0m");
        res.append("\t\033[31mTotal Payment Balance: ").append(totalPayments).append("\n\033[0m");
        res.append("\tTotal Account Balance: ").append(accountBalance < 0 ? "\033[31m": "\033[32m").append(accountBalance).append("\n\033[0m");
        res.append("---------------------------------------------------").append("\n\033[0m");

        return res.toString();
    }

    public static String getDeposits(){

        StringBuilder res = new StringBuilder("\nAll Deposit Transactions:\nDate|Time|Description|Vendor|Amount\n");
        res.append("---------------------------------------------------").append("\n");

        float accountBalance = 0;
        float totalDeposits = 0;

        ArrayList<Transaction> transactions = FileManager.read(path);
        if(transactions == null) return "Unable to Load Transactions";

        for(Transaction transaction: transactions){
            if(!transaction.isPayment){
                res.append(transaction.toPrettyString()).append("\n");
                totalDeposits += transaction.amount;
            }
            accountBalance += transaction.amount;
        }

        res.append("---------------------------------------------------\nSummary: ").append("\n\033[0m");
        res.append("\t\033[32mDeposit Balance: ").append(totalDeposits).append("\n\033[0m");
        res.append("\tTotal Account Balance: ").append(accountBalance < 0 ? "\033[31m": "\033[32m").append(accountBalance).append("\n\033[0m");
        res.append("---------------------------------------------------").append("\n\033[0m");

        return res.toString();
    }


    public static String getPayments(){

        StringBuilder res = new StringBuilder("\nAll Payment Transactions:\nDate|Time|Description|Vendor|Amount\n");
        res.append("---------------------------------------------------").append("\n");

        float accountBalance = 0;
        float totalPayments = 0;

        ArrayList<Transaction> transactions = FileManager.read(path);
        if(transactions == null) return "Unable to Load Transactions";

        for(Transaction transaction: transactions){
            if(transaction.isPayment){
                res.append(transaction.toPrettyString()).append("\n");
                totalPayments += transaction.amount;
            }
            accountBalance += transaction.amount;
        }

        res.append("---------------------------------------------------\nSummary: ").append("\n\033[0m");
        res.append("\t\033[31mTotal Payment Balance: ").append(totalPayments).append("\n\033[0m");
        res.append("\tTotal Account Balance: ").append(accountBalance < 0 ? "\033[31m": "\033[32m").append(accountBalance).append("\n\033[0m");
        res.append("---------------------------------------------------").append("\n\033[0m");

        return res.toString();
    }


//USED BY REPORTS MENU
    //MONTH TO DATE OPTION
    public static String monthToDate(){
        StringBuilder res = new StringBuilder("\nAll Available Transactions, Month to Date:\nDate|Time|Description|Vendor|Amount\n");
        res.append("---------------------------------------------------").append("\n\033[0m");

        float accountBalance = 0;
        float totalDeposits = 0;
        float totalPayments = 0;
        LocalDateTime now = LocalDateTime.now();

        ArrayList<Transaction> transactions = FileManager.read(path);
        if(transactions == null) return "Unable to Load Transactions";

        for(Transaction transaction: transactions){
            if(transaction.getDateTime().isBefore(now) && transaction.getDateTime().getMonth().equals(now.getMonth()) && transaction.getDateTime().getYear() == now.getYear()){
                if(transaction.isPayment) totalPayments += transaction.amount; else totalDeposits += transaction.amount;
                res.append(transaction.toPrettyString()).append("\n");
            }
            accountBalance += transaction.amount;
        }
        res.append("---------------------------------------------------\nSummary: ").append("\n\033[0m");
        res.append("\t\033[32mDeposits MTD: ").append(totalDeposits).append("\n\033[0m");
        res.append("\t\033[31mTotal Payments MTD: ").append(totalPayments).append("\n\033[0m");
        float sum = totalDeposits + totalPayments;
        res.append("\tNet Total MTD: ").append(sum < 0 ? "\033[31m": "\033[32m").append(sum).append("\n\033[0m");
        res.append("\tTotal Account Balance: ").append(accountBalance < 0 ? "\033[31m": "\033[32m").append(accountBalance).append("\n\033[0m");
        res.append("---------------------------------------------------").append("\n\033[0m");
        return res.toString();


    }

    //PREVIOUS MONTH OPTION
    public static String previousMonth(){
        StringBuilder res = new StringBuilder("\nAll Available Transactions, Previous Month:\nDate|Time|Description|Vendor|Amount\n");
        res.append("---------------------------------------------------").append("\n");

        float accountBalance = 0;
        float lastMonthBalance = 0;
        float totalDeposits = 0;
        float totalPayments = 0;

        LocalDate now = LocalDate.now();

        ArrayList<Transaction> transactions = FileManager.read(path);
        if(transactions == null) return "Unable to Load Transactions";

        for(Transaction transaction: transactions){
                if(transaction.getDateTime().getMonth().equals(now.getMonth().minus(1)) && transaction.getDateTime().getYear() == now.getYear()){
                    if(transaction.isPayment) totalPayments += transaction.amount; else totalDeposits += transaction.amount;
                    res.append(transaction.toPrettyString()).append("\n");
                }
                if(transaction.getDateTime().getMonth().getValue() < now.getMonth().getValue() && transaction.getDateTime().getYear() <= now.getYear()) lastMonthBalance += transaction.amount;
                accountBalance += transaction.amount;
            }

        res.append("---------------------------------------------------\nSummary: ").append("\n\033[0m");
        res.append("\t\033[32mDeposits Last Month: ").append(totalDeposits).append("\n\033[0m");
        res.append("\t\033[31mPayments Last Month: ").append(totalPayments).append("\n\033[0m");
        float sum = totalDeposits + totalPayments;
        res.append("\tNet Change Last Month: ").append(sum < 0 ? "\033[31m": "\033[32m").append(sum).append("\n\033[0m");
        res.append("\tBalance Last Month: ").append(lastMonthBalance < 0 ? "\033[31m": "\033[32m").append(lastMonthBalance).append("\n\033[0m");
        res.append("\tCurrent Account Balance: ").append(accountBalance < 0 ? "\033[31m": "\033[32m").append(accountBalance).append("\n\033[0m");
        res.append("---------------------------------------------------").append("\n\033[0m");

        return res.toString();
    }

    //YEAR TO DATE OPTION
    public static String yearToDate(){
        StringBuilder res = new StringBuilder("\nAll Available Transactions, Year to Date:\nDate|Time|Description|Vendor|Amount\n");
        res.append("---------------------------------------------------").append("\n");

        float accountBalance = 0;
        float totalDeposits = 0;
        float totalPayments = 0;

        LocalDateTime now = LocalDateTime.now();

        ArrayList<Transaction> transactions = FileManager.read(path);
        if(transactions == null) return "Unable to Load Transactions";

        for(Transaction transaction: transactions){
            if(transaction.getDateTime().isBefore(now) && transaction.getDateTime().getYear() == now.getYear()){
                if(transaction.isPayment) totalPayments += transaction.amount; else totalDeposits += transaction.amount;
                res.append(transaction.toPrettyString()).append("\n");
            }
            accountBalance += transaction.amount;
        }

        res.append("---------------------------------------------------\nSummary: ").append("\n\033[0m");
        res.append("\t\033[32mDeposits YTD: ").append(totalDeposits).append("\n\033[0m");
        res.append("\t\033[31mPayments YTD: ").append(totalPayments).append("\n\033[0m");
        float sum = totalDeposits + totalPayments;
        res.append("\tNet Change YTD: ").append(sum < 0 ? "\033[31m": "\033[32m").append(sum).append("\n\033[0m");
        res.append("\tCurrent Account Balance: ").append(accountBalance < 0 ? "\033[31m": "\033[32m").append(accountBalance).append("\n\033[0m");
        res.append("---------------------------------------------------").append("\n\033[0m");

        return res.toString();
    }

    //PREVIOUS YEAR OPTION
    public static String previousYear() {
        StringBuilder res = new StringBuilder("\nAll Available Transactions, Previous Year:\nDate|Time|Description|Vendor|Amount\n");
        res.append("---------------------------------------------------").append("\n");

        float accountBalance = 0;
        float lastYearBalance = 0;
        float totalDeposits = 0;
        float totalPayments = 0;

        LocalDate now = LocalDate.now();

        ArrayList<Transaction> transactions = FileManager.read(path);
        if(transactions == null) return "Unable to Load Transactions";

        for(Transaction transaction: transactions){
            if(transaction.getDateTime().getYear() == now.getYear() - 1){
                if(transaction.isPayment) totalPayments += transaction.amount; else totalDeposits += transaction.amount;
                res.append(transaction.toPrettyString()).append("\n");
            }
            if(transaction.getDateTime().getYear() <= now.getYear() - 1) lastYearBalance += transaction.amount;
            accountBalance += transaction.amount;
        }

        res.append("---------------------------------------------------\nSummary: ").append("\n\033[0m");
        res.append("\t\033[32mDeposits Last Year: ").append(totalDeposits).append("\n\033[0m");
        res.append("\t\033[31mPayments Last Year: ").append(totalPayments).append("\n\033[0m");
        float sum = totalDeposits + totalPayments;
        res.append("\tNet Change Last Year: ").append(sum < 0 ? "\033[31m": "\033[32m").append(sum).append("\n\033[0m");
        res.append("\tBalance Last Year: ").append(lastYearBalance < 0 ? "\033[31m": "\033[32m").append(lastYearBalance).append("\n\033[0m");
        res.append("\tCurrent Account Balance: ").append(accountBalance < 0 ? "\033[31m": "\033[32m").append(accountBalance).append("\n\033[0m");
        res.append("---------------------------------------------------").append("\n\033[0m");

        return res.toString();

    }

    //SEARCH BY VENDOR OPTION
    public static String searchByVendor(String vendorName){
        if(vendorName == null || vendorName.isBlank()) return "\033[33mSearch Cancelled\033[0m";
        StringBuilder res = new StringBuilder("\nAll Available Transactions Under Vendor: ").append("\033[1;33m").append(vendorName).append("\033[0m")
                .append("\nDate|Time|Description|Vendor|Amount\n");
        res.append("---------------------------------------------------").append("\n");

        float vendorPayments = 0;
        float vendorDeposits = 0;

        ArrayList<Transaction> transactions = FileManager.read(path);
        if(transactions == null) return "Unable to Load Transactions";
        for(Transaction transaction: transactions){
            if(transaction.vendor.equalsIgnoreCase(vendorName)){
                if(transaction.isPayment) vendorPayments += transaction.amount; else vendorDeposits += transaction.amount;
                res.append(transaction.toPrettyString()).append("\n");
            }
        }

        res.append("---------------------------------------------------\nSummary: ").append("\n\033[0m");
        res.append("\t\033[32mVendor Deposits: ").append(vendorDeposits).append("\n\033[0m");
        res.append("\t\033[31mVendor Payments: ").append(vendorPayments).append("\n\033[0m");
        float sum = vendorDeposits + vendorPayments;
        res.append("\tVendor Net Total: ").append(sum < 0 ? "\033[31m": "\033[32m").append(sum).append("\n\033[0m");
        res.append("---------------------------------------------------").append("\n\033[0m");

        return res.toString();
    }

    //SEARCH BY ALL OPTION
    public static String searchByAll(LocalDateTime startDate, LocalDateTime endDate, String desc, String vendorName, Float minPrice, Float maxPrice){
        StringBuilder res = new StringBuilder("\nAll Available Transactions Matching Search:\nDate|Time|Description|Vendor|Amount\n");
        res.append("---------------------------------------------------").append("\n");

        float searchPayments = 0;
        float searchDeposits = 0;

        ArrayList<Transaction> transactions = FileManager.read(path);
        if(transactions == null) return "Unable to Load Transactions";

        for(Transaction transaction: transactions){
            LocalDateTime date = transaction.getDateTime();
            if(
                    (startDate == null || date.isAfter(startDate)) &&
                    (endDate == null || date.isBefore(endDate)) &&
                    (desc == null || desc.isBlank() || transaction.description.equalsIgnoreCase(desc)) &&
                    (vendorName == null || vendorName.isBlank() || transaction.vendor.equalsIgnoreCase(vendorName)) &&
                    (minPrice == null || minPrice <= 0 || transaction.getAbsPrice() >= minPrice) &&
                    (maxPrice == null || maxPrice <= 0 || transaction.getAbsPrice() <= maxPrice)

            ){
                if(transaction.isPayment) searchPayments += transaction.amount; else searchDeposits += transaction.amount;
                res.append(transaction.toPrettyString()).append("\n");
            }
        }

        res.append("---------------------------------------------------\nSummary: ").append("\n\033[0m");
        res.append("\t\033[32m Search Deposits: ").append(searchDeposits).append("\n\033[0m");
        res.append("\t\033[31m Search Payments: ").append(searchPayments).append("\n\033[0m");
        float sum = searchDeposits + searchPayments;
        res.append("\tSearch Net Total: ").append(sum < 0 ? "\033[31m": "\033[32m").append(sum).append("\n\033[0m");
        res.append("---------------------------------------------------").append("\n\033[0m");

        return res.toString();
    }
}
