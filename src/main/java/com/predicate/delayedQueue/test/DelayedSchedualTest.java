package com.predicate.delayedQueue.test;

import com.predicate.delayedQueue.service.DelayedServiceThread;
import com.predicate.delayedQueue.task.DelayedTask;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  8:10 2018/5/20
 * @ModefiedBy:
 */
public class DelayedSchedualTest {

    public static void main(String[] args) {
        DelayedServiceThread thread=DelayedServiceThread.getInstance();
        thread.init();
        Random random=new Random();
        for (int i=0;i<10;i++){
            int time=random.nextInt(15);
            System.out.println(" need to wait for   " + time);
            DelayedTask task=new DelayedTask("  task "+i,time);
            thread.putTask(time,task, TimeUnit.SECONDS);
        }
    }
}
