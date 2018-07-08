package com.fusong.test;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  16:16 2018/6/29
 * @ModefiedBy:
 */
public class DoublePortWork {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("aaa");
        new Thread(new Worker()).start();
        System.out.println("bbb");
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);
        blockingQueue.put("aaa");
        blockingQueue.put("bbb");
        blockingQueue.put("ccc");
        blockingQueue.put("ddd");
        blockingQueue.put("eee");

        threadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String elem=blockingQueue.peek();
                System.out.println(" current  elem  "+elem);
                try {
                    blockingQueue.put(blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 10, 1,TimeUnit.SECONDS);
        System.out.println("ddd");
    }

    static class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println(new Date().getSeconds());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
