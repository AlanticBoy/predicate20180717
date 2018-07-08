package com.fusong.utils;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  19:27 2018/5/2
 * @ModefiedBy:
 */
public class SwingUtil {
    /**
     * 蜂鸣声音
     */
    public static void bee() {
        for (int k = 0; k < 5; k++) {
            if (isBee) {
                Frame f = new Frame();
                Toolkit kit = f.getToolkit();
                kit.beep();
            }
            System.err.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "------------警报...warning....");
            try {
                Thread.sleep(1 * 250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isBee = true;

    public static void main(String[] args) {
       bee();
    }


}
