package com.predicate.user.POJO;

import java.io.Serializable;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  16:55 2018/5/21
 * @ModefiedBy:
 */
public class UserInfo implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }


    public UserInfo(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
