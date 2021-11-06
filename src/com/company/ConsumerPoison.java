package com.company;

import java.util.concurrent.BlockingQueue;

public class ConsumerPoison implements Runnable {

    private final BlockingQueue queue;
    private final String POISON;

    @Override
    public void run() {

        try {
            while (true) {
                String take = (String) queue.take();
                process(take);

// если это ядовитая таблетка, ломайся, выходи
                if (take == POISON) {
                    break;
                }

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private void process(String take) throws InterruptedException {
        System.out.println("[Consumer] Take : " + take);

    }

    public ConsumerPoison(BlockingQueue queue, String POISON) {
        this.queue = queue;
        this.POISON = POISON;
    }
}
