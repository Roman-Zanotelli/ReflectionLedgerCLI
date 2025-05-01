package com.pluralsight.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FileManager {




    public static String write(Transaction transaction, String path){
        try {
            File target = new File(path);
            boolean existedAlready = target.exists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(target, true));
            if(!existedAlready){
                writer.append("date|time|description|vendor|amount\n");
            }
            writer.append(String.format("%s\n", transaction));
            writer.close();

        } catch (IOException e) {
            return String.format("\033[31mI/O ERROR OCCURRED!\033[0m\nTransaction not written to file:\n%s\n", transaction);
        }
        return String.format("\033[1;32mSuccess!\nTransaction Written To File:\033[0m\n%s\n", transaction.toPrettyString());
    }

    public static ArrayList<Transaction> read(String path) {
        ArrayList<Transaction> list = new ArrayList<>();
        try {
            File target = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(target));

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                list.add(new Transaction(line));
            }
            reader.close();
        }catch (Exception e ){
            return null;
        }
        list.sort(Comparator.comparing(Transaction::getDateTime));


       Collections.reverse(list);
        return list;
    }
}
