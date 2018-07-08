package com.predicate.redis.redisqueue;

import com.fusong.utils.JedisUtils;
import com.fusong.utils.SerizableUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  17:35 2018/5/20
 * @ModefiedBy:
 */
public class RedisProducer implements Runnable {

    private String name;
    private String uuid;
    private Map<String, Object> map = new HashMap<>();

    public RedisProducer(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
        map.put("name", name);
        map.put("uuid", uuid);
    }

    @Override
    public void run() {
        try {

            JedisUtils.lpush("temp_queue".getBytes(), SerizableUtils.serizeObject(map));
            System.out.println(" put  " + map.get("name") + " into redis queue ");
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
