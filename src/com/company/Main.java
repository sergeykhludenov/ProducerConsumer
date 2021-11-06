package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Parser parser = new Parser();
        ArrayList<String> list = null;
        File file = new File("voyna.txt");
        try {
            list = parser.parse(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Поиск количества вхождений конкретного слова в файле с помощью BufferedReader
        int wordCount = getBufferWordCount(list);

        BlockingQueue queue = new LinkedBlockingQueue<>(20);

        String poison = "";
        new Thread(new ProducerPoison(queue, poison)).start();
        new Thread(new ConsumerPoison(queue, poison)).start();


    }

    // Поиск количества вхождений конкретного слова в файле с помощью BufferedReader
    private static int getBufferWordCount(ArrayList<String> list) {
        int wordCount = 0;
        Pattern pattern = Pattern.compile("страдание", Pattern.CASE_INSENSITIVE);

        for (String s : list) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                wordCount++;
            }
        }
        return wordCount;
    }




}
