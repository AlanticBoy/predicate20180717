package com.fusong.test;

import org.testng.annotations.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  17:03 2018/5/19
 * @ModefiedBy:
 */
public class DequeDemo {

    @Test
    public void test1(){
        Deque<String> strings=new LinkedList<>();
        strings.push("111");
        strings.push("222");
        strings.push("333");
        strings.push("444");
        System.out.println(strings.removeFirst());
        System.out.println(strings.removeLast());
    }
}
