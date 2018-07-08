package com.predicate.user.cacheManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  21:01 2018/5/21
 * @ModefiedBy:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext_ehcache.xml")
public class CacheTest {

    @Autowired
    private CacheUtil cacheUtil;


    @Test
    public void test1() {
        cacheUtil.addDataIntoCache("name", "fuspong");

        System.out.println(cacheUtil.getDataFromCache("name"));

    }

    @Test
    public void test2() {
        System.out.println(cacheUtil.getDataFromCache("name"));

    }
}
