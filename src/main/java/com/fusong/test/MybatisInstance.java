package com.fusong.test;

import com.predicate.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  15:23 2018/5/20
 * @ModefiedBy:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext_mybatis.xml"})
public class MybatisInstance {

    @Autowired
   private UserService userService;

    @Test
    public void test1(){
        Map<String,Object> map=new HashMap<>();
        map.put("phone","13633809314");
        map.put("email","1725383737@qq.com");
        map.put("password","admin");
        int id=userService.addNewUser(map);
        System.out.println("  get PrimaryKey val  "+id);
        System.out.println("  get PrimaryKey val  "+map.get("userId"));
    }

    @Test
    public void test2(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","sherry");
        map.put("phone","13633809314");
        int id=userService.updateUser(map);
        System.out.println(" get updated primaryKey val "+map.get("userId"));
    }
}
