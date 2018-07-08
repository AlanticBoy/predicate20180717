package com.fusong.utils;

import java.util.*;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  19:46 2018/6/7
 * @ModefiedBy:
 */
public class DifferenceBetween7_8 {

    public static void main(String[] args) {
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");

        sortBy_7(names1);
        ListIterator<String> iterator = names1.listIterator();
        while (iterator.hasNext()) {
            System.out.print("  " + iterator.next());
        }
    }

    public static void sortBy_7(List<String> names) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

}
