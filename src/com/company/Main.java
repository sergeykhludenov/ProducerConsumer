package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
        System.out.println("Количество слов <страдание> и производных от него: " + wordCount);

        // Поиск количества вхождений введённого через консоль слова с помощью Scanner
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        int newWordCount = getScannerWordCount(string, list);
        System.out.println("Количество слов" + string + ": " + newWordCount);

        // Выровненная таблица умножения
        System.out.println("Таблица умножения");
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <=10 ; j++) {
                if (i*j < 10) {
                    System.out.print(i * j + "  ");
                } else {
                    System.out.print(i * j + " ");
                }
            }
            System.out.println("");
        }
    }

    // Поиск количества вхождений конкретного слова в файле с помощью BufferedReader
    private static int getBufferWordCount(ArrayList<String> list) {
        int wordCount = 0;
        String word = "страдани[еяюй]??";
        Pattern pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);

        for (String s : list) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                wordCount++;
            }
        }
        return wordCount;
    }

    //Поиск количества вхождений введённого через консоль слова с помощью Scanner
    private static int getScannerWordCount(String word, ArrayList<String> list) {
        int wordCount = 0;
        Pattern pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);

        for (String s : list) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                wordCount++;
            }
        }
        return wordCount;
    }
}
