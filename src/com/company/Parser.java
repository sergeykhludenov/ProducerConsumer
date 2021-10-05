package com.company;

import java.io.*;
import java.util.ArrayList;

public class Parser {

    public ArrayList<String> parse(File file) throws IOException {
        BufferedReader reader = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = reader.readLine();
        while (line != null) {
            list.add(line);
            line = reader.readLine();
        }
        return list;
    }
}
