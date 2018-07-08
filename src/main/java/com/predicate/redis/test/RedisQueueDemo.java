package com.predicate.redis.test;

import com.fusong.utils.RandomUtil;
import com.predicate.redis.redisqueue.RedisCosumer;
import com.predicate.redis.redisqueue.RedisProducer;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  17:45 2018/5/20
 * @ModefiedBy:
 */
public class RedisQueueDemo {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            threadPool.execute(new RedisProducer("Producer" + i, RandomUtil.randomNumberString(8)));
        }
        threadPool.execute(new RedisCosumer());
        threadPool.shutdown();
    }

}
