package com.predicate.delayedQueue.service;

import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  21:37 2018/5/19
 * @ModefiedBy:
 */
public class TaskExecution implements Runnable {

    private ExecutorService threadPool;
    private DelayQueue<DelayedService<?>> delayQueue;

    public TaskExecution(ExecutorService threadPool, DelayQueue<DelayedService<?>> delayQueue) {
        this.threadPool = threadPool;
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                DelayedService<? extends Runnable> service=delayQueue.take();
                Runnable task=null;
                task=service.getTask();
                if (task!=null){
                    threadPool.execute(task);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
