package com.fusong.utils;

import java.io.*;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  13:21 2018/5/4
 * @ModefiedBy:
 */
public class SerizableUtils {

      /*把对象序列化成字节数组*/
    public static byte[] serizeObject(Object obj) throws IOException {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        ObjectOutputStream oo=new ObjectOutputStream(outputStream);
        oo.writeObject(obj);
        byte []bytes=outputStream.toByteArray();
        outputStream.close();
        oo.close();
        return bytes;
    }
    /*把byte数组反序列化成对象*/
    public static Object unserizeObject(byte []bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream=new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);
        return objectInputStream.readObject();
    }
}
