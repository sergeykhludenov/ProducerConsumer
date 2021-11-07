package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProducerPoison implements Runnable {

    private final BlockingQueue queue;
    private final String POISON;
    private ArrayList<String> list;

    ProducerPoison(
            BlockingQueue queue,
            String POISON,
            ArrayList<String> list
    ) {
        this.queue = queue;
        this.POISON = POISON;
        this.list = list;
    }

    @Override
    public void run() {

        try {
            process();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            while (true) {
                try {
                    queue.put(POISON);
                    break;
                } catch (InterruptedException e) {
// ...
                }
            }
        }

    }

    private void process() throws InterruptedException {
        // Поместить элементы в очередь
        Pattern pattern = Pattern.compile("страдание", Pattern.CASE_INSENSITIVE);
        String word = null;
        for (String s : list) {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                word = matcher.group();
                System.out.println("[Producer] Put : " + word);
                queue.put(word);
            }
        }

    }


}
