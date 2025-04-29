package com.pluralsight.util;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    LocalDate date;
    LocalTime time;
    String description;
    String vendor;
    float amount;
    boolean isPayment;

    public Transaction(float amount, String description, String vendor, boolean isPayment){
        this.description = description;
        this.vendor = vendor;
        this.amount = amount < 0 ? amount * -1 : amount;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.isPayment = isPayment;
    }
    public Transaction(String _string){
        String[] tokens = _string.split("\\|");
        this.date = LocalDate.parse(tokens[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.time = LocalTime.parse(tokens[1], DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.description = tokens[2];
        this.vendor = tokens[3];
        this.amount = Float.parseFloat(tokens[4].replace("$", ""));
        if(this.amount < 0){
            isPayment = true;
            this.amount *= -1;
        }else isPayment = false;
    }
    public boolean isPayment(){
        return isPayment;
    }


    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s$%.2f", date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), time.format(DateTimeFormatter.ofPattern("HH:mm:ss")), description, vendor, isPayment ? "-" : "",amount);
    }
}
