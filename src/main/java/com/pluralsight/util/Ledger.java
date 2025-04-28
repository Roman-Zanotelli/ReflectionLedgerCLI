package com.pluralsight.util;
import java.util.Date;

public class Ledger {
    static String username;
    static String nPII;
//USED BY START MENU
    public static String login(String userName, String pass, Integer pin){
        //Login Logic Here
        return null;
    }
//USED BY HOME MENU
    public static String addDeposit(Float amount, String description, String vendor){
        return "TODO";
    }
    public static String makePayment(Float amount, String description, String vendor) {
        return "TODO";
    }
    public static String logout(){
        return "TODO";
    }

//USED BY LEDGER MENU
    public static String getAll(){
        return "TODO";
    }
    public static String getDeposits(){
        return "TODO";
    }
    public static String getPayments(){
        return "TODO";
    }


//USED BY REPORTS MENU
    //MONTH TO DATE OPTION
    public static String monthToDate(){
        //MONTH TO DATE LOGIC HERE
        return "TODO";
    }

    //PREVIOUS MONTH OPTION
    public static String previousMonth(){
        //PREVIOUS MONTH LOGIC
        return "TODO";
    }

    //YEAR TO DATE OPTION
    public static String yearToDate(){
        //YEAR TO DATE LOGIC HERE
        return "TODO";
    }

    //PREVIOUS YEAR OPTION
    public static String previousYear() {
        //PREVIOUS YEAR LOGIC HERE
        return "TODO";
    }

    //SEARCH BY VENDOR OPTION
    public static String searchByVendor(String vendorName){
        //SEARCH BY VENDOR LOGIC HERE
        return "TODO";
    }

    //SEARCH BY ALL OPTION
    public static String searchByAll(Date startDate, Date endDate, String desc, String vendorName, Float minPrice, Float maxPrice){
        //SEARCH BY ALL LOGIC HERE
        return "TODO";
    }

//USED ON EXIT
    public static void close(){}
}
