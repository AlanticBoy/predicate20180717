package com.predicate.delayedQueue.service;

import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  21:31 2018/5/19
 * @ModefiedBy:
 */
public class DelayedServiceThread {

    /*-----------------懒加载单例模式创建对象-----------------*/
    private DelayedServiceThread() {
    }

    private static class LazyHolder {
        private static DelayedServiceThread thread = new DelayedServiceThread();
    }

    public static DelayedServiceThread getInstance() {
        return LazyHolder.thread;
    }

    /*-----------------懒加载单例模式获取对象完毕-----------------*/
    ExecutorService threadPool = Executors.newFixedThreadPool(10);
    /*创建延迟队列*/
    DelayQueue<DelayedService<? extends Runnable>> delayQueue = new DelayQueue<>();

    /*创建线程*/
    private Thread dazeThread;

    public void init() {
        dazeThread = new Thread(new TaskExecution(threadPool,delayQueue));
        dazeThread.start();
    }
    /*往队列里添加任务*/
    public void putTask(long time, Runnable task, TimeUnit timeUnit){
        long nanoTime = TimeUnit.MILLISECONDS.convert(time, timeUnit);
        /*每次创建一个DelayedService对象用来装载Task*/
        DelayedService<? extends Runnable> service=new DelayedService<>(nanoTime,task);
        delayQueue.put(service);
    }

}
