package com.fusong.test;

import com.fusong.utils.RandomUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  19:37 2018/6/7
 * @ModefiedBy:
 */
public class ThreadDemo {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Outputor(lock,RandomUtil.randomString(8)));
        }
        threadPool.shutdown();
    }

    static class Outputor implements Runnable {

        private Lock lock;
        private String string;

        public Outputor(Lock lock, String string) {
            this.lock = lock;
            this.string = string;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < string.length(); i++) {
                    System.out.print(" " + string.charAt(i));
                }
                System.out.println("");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }
}
