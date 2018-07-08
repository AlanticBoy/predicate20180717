package com.fusong.test;

import com.predicate.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  10:52 2018/5/21
 * @ModefiedBy:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext_mybatis.xml")
public class TransactionManagerDemo {

    @Autowired
    private UserService userService;
    @Test
    public void testService() {
    userService.updateAfterInsertUser();

    }
}
