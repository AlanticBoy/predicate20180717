package com.fusong.ActiveQueue;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  21:19 2018/5/4
 * @ModefiedBy:
 */
public class HttpClientMessage {

    public static void main(String[] args) {
        ExecutorService threadPool=Executors.newCachedThreadPool();

        for (int i=0;i<100;i++){
            HttpClient httpClient=new HttpClient();
            threadPool.execute(new Sender(httpClient));
        }
        threadPool.shutdown();
    }

    static class Sender implements Runnable {
        public static AtomicInteger count = new AtomicInteger(0);
        HttpClient httpClient;

        public Sender(HttpClient client) {
            httpClient = client;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println(Thread.currentThread().getName()+"---Send message-"+count.getAndIncrement());
                PostMethod postMethod=new PostMethod("http://127.0.0.1:8080/message/SendMessage");
                postMethod.addParameter("mesg","I love you");
                httpClient.executeMethod(postMethod);
                System.out.println(Thread.currentThread().getName()+"---Send message Success-"+count.getAndIncrement());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
