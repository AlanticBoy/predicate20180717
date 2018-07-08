package com.fusong.POJO;

import java.io.Serializable;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  13:27 2018/5/4
 * @ModefiedBy:
 */
public class Mesage implements Serializable {
    private static final long serialVersionUID = -389326121047047723L;
    private int id;
    private String content;
    public Mesage(int id, String content) {
        this.id = id;
        this.content = content;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
