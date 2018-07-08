package com.fusong.test;

import com.fusong.POJO.Mesage;
import com.fusong.utils.JedisUtils;
import com.fusong.utils.SerizableUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  13:36 2018/5/4
 * @ModefiedBy:
 */
public class JedisDemo {

    @Test
    public void test1() {
        JedisUtils.set("fusong".getBytes(), "handsome".getBytes());
        JedisUtils.set("fuchouyi".getBytes(), "ugrl".getBytes(), 15);

        String val = JedisUtils.get("fuchouyi");
        System.out.println("  " + val);
        Mesage mesage = new Mesage(1000, "你好啊，世界");
        try {
            JedisUtils.set("hello".getBytes(), SerizableUtils.serizeObject(mesage));
            Mesage mesage1 = (Mesage) SerizableUtils.unserizeObject(JedisUtils.get("hello".getBytes()));
            System.out.println("   " + mesage1.getContent() + " id  " + mesage1.getId());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        JedisUtils.hset("mesmap", "id", "0002");
        JedisUtils.hset("mesmap", "content", "yes,I can");
        String val = JedisUtils.hget("mesmap", "content");
        System.out.println(val);

        Map<String, String> map = new HashMap<>();
        map.put("id", "0001");
        map.put("name", "fuchouyi");
        map.put("sex", "nan");
        map.put("address", "hebei");
        JedisUtils.set("userInfo".getBytes(), SerizableUtils.serizeObject(map));
        Map<String, String> stringMap = (Map<String, String>) SerizableUtils.unserizeObject(JedisUtils.get("userInfo".getBytes()));
        System.out.println(" name " + stringMap.get("name"));
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "songsong");
        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "fusong");
        Map<String, String> map3 = new HashMap<>();
        map3.put("name", "xiao fu");
        maps.add(map1);
        maps.add(map2);
        maps.add(map3);
        JedisUtils.set("mapList".getBytes(), SerizableUtils.serizeObject(maps));
        List<Map<String, String>> mapList = (List<Map<String, String>>) SerizableUtils.unserizeObject(JedisUtils.get("mapList".getBytes()));

        for (Map<String, String> map4 : mapList) {
            System.out.println(" name of Map " + map4.get("name"));
        }
    }

    /*满足先进先出*/
    @Test
    public void test3() throws IOException, ClassNotFoundException {
        JedisUtils.lpush("userList".getBytes(), "fuyiyi".getBytes());
        JedisUtils.lpush("userList".getBytes(), "14sui".getBytes());
        JedisUtils.lpush("userList".getBytes(), "shuai qi".getBytes());
        JedisUtils.lpush("userList".getBytes(), "bu ting hua".getBytes());
        JedisUtils.lpush("userList".getBytes(), "has a brother".getBytes());
        JedisUtils.lpush("userList".getBytes(), "whose name is fusong".getBytes());
        JedisUtils.lpush("userList".getBytes(), "stubborn".getBytes());
        byte bytes[] = null;
      /*  while ((bytes = JedisUtils.rpop("userList".getBytes())) != null) {
            System.out.println(" dest  =  " + new String(bytes));
        }*/


    }

    @Test
    public void test4() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "yiyi");
        map.put("age", "14");
        JedisUtils.hmset("mapUser".getBytes(), map, 15);
        List list = JedisUtils.hmget("mapUser", "name", "age");
        System.out.println(list.get(0));
    }


}
