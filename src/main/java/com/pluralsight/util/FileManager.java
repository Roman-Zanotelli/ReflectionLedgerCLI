package com.pluralsight.util;

import java.io.*;
import java.util.ArrayList;

public class FileManager {




    public static String write(Transaction transaction, String path){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(String.format("%s\n", transaction));
            writer.close();
        } catch (IOException e) {
            return String.format("I/O ERROR OCCURRED!\nTransaction not written to file:\n%s\n", transaction);
        }
        return String.format("Success!\nTransaction Written To File:\n%s\n", transaction);
    }
    public static ArrayList<Transaction> read(String path) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        ArrayList<Transaction> list = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null){
            list.add(new Transaction(line));
        }
        reader.close();
        return list;
    }
    public static void close(){

    }
}
