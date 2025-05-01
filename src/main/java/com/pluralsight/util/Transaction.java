package com.pluralsight.util;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    LocalDateTime dateTime;
    String description;
    String vendor;
    float amount;
    boolean isPayment;

    public Transaction(float amount, String description, String vendor, boolean isPayment){
        this.description = description;
        this.vendor = vendor;
        this.amount = amount > 0 && isPayment ? amount * -1 : amount;
        this.dateTime = LocalDateTime.now();
        this.isPayment = isPayment;
    }
    public Transaction(String _string){
        String[] tokens = _string.split("\\|");
        this.dateTime = LocalDateTime.parse(tokens[0] + "|" + tokens[1], DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss"));
        this.description = tokens[2];
        this.vendor = tokens[3];
        this.amount = Float.parseFloat(tokens[4].replace("$", ""));
        this.isPayment = this.amount < 0;
    }
    public boolean isPayment(){
        return isPayment;
    }
    public float getAbsPrice(){
        return Math.abs(amount);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|$%.2f", dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss")), description, vendor,amount);
    }
    public String toPrettyString(){
        return String.format("%s%s|%s|%s|$%.2f\033[0m", isPayment? "\033[31m" : "\033[0;32m", dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss")), description, vendor,amount);
    }
}
