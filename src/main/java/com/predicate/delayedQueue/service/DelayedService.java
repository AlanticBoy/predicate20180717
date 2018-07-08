package com.predicate.delayedQueue.service;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  21:22 2018/5/19
 * @ModefiedBy:
 */
public class DelayedService<T extends Runnable> implements Delayed {

    /*设置延迟时间*/
    private long delayTime;
    /*设置到期时间;*/
    private long expireTime;
    /*任务*/
    private T task;


    public DelayedService(long delayTime, T task) {
        this.delayTime = delayTime;
        this.task = task;
    /*注意:过期时间=延迟时间+当前时间*/
        expireTime = delayTime + System.currentTimeMillis();
    }

    public T getTask() {
        return this.task;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expireTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed obj) {
        long leftTime = (getDelay(TimeUnit.MILLISECONDS) - obj.getDelay(TimeUnit.MILLISECONDS));
        return (leftTime == 0) ? 0 : ((leftTime < 0) ? -1 : 1);
    }
}
