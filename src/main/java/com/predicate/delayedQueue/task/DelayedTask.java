package com.predicate.delayedQueue.task;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  8:01 2018/5/20
 * @ModefiedBy:
 */
public class DelayedTask implements Runnable {

    private String taskName;
    private int val;

    public DelayedTask(String taskName, int val) {
        this.taskName = taskName;
        this.val = val;
    }

    @Override
    public void run() {
        System.out.println(taskName + "  will be executed after   " + val + "  seconds ");
    }
}
