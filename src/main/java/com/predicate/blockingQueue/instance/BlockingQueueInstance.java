package com.predicate.blockingQueue.instance;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.fusong.utils.CharacterUtils;

public class BlockingQueueInstance {

    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);

    private static ExecutorService threadPool = Executors.newFixedThreadPool(20);

    private static Map<String, String> map = new ConcurrentHashMap<>();

    private static Lock lock = new ReentrantLock();

    public BlockingQueueInstance() {
        try {
            blockingQueue.put("  happy  ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void execute() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            String randomString = CharacterUtils.getRandomString(8);
            threadPool.execute(new Producer(randomString));
            threadPool.execute(new Customer());
        }
        threadPool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println("  key set list   " + iterator.next());
        }
        threadPool.shutdown();
    }

    static class Producer implements Runnable {

        String str = "";

        public Producer(String str) {
            this.str = str;
        }

        @Override
        public void run() {

            try {
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("produce data into queue :" + str);
                blockingQueue.put(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class Customer implements Runnable {

        @Override
        public void run() {
            String string;
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                string = blockingQueue.take();
                System.out.println("get data from queue :" + string);
                if (map.get(string) == null) {
                    map.put(string, string);

                    new HandlerString(string).start();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class HandlerString extends Thread {

        private String str;
        public HandlerString(String str) {
            this.str = str;
        }

        public void print() {
            lock.lock();
            try {
                System.out.println(" has enter thread of HandlerString, waiting to execute ");
                for (int i = 0; i < str.length(); i++) {
                    System.out.print(" " + str.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void run() {
            print();
        }

    }

}
