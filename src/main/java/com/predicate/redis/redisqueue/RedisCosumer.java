package com.predicate.redis.redisqueue;

import com.fusong.utils.JedisUtils;
import com.fusong.utils.SerizableUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  17:41 2018/5/20
 * @ModefiedBy:
 */
public class RedisCosumer implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                Map<String, Object> map = (Map<String, Object>) SerizableUtils.unserizeObject(JedisUtils.rpop("temp_queue".getBytes()));
                if (map != null) {
                    System.out.println(" consumer has taken data where name = " + map.get("name") + " uuid " + map.get("uuid"));
                }else {
                    System.out.println("  have no data ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
