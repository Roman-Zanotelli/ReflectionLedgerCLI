package com.pluralsight.util;

public class Ledger {
    private final static Ledger ledger = new Ledger();
    public static Ledger getInstance(){
        return ledger;
    }
    public static String getDeposits(){
        return "TODO";
    }
    public static String getPayments(){
        return "TODO";
    }
    @Override
    public String toString() {
        return "TODO";
    }
    public static void close(){}
}
