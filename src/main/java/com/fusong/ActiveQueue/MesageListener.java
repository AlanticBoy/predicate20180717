package com.fusong.ActiveQueue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author:付风松
 * @Description:在实际项目中，我们很少会自己手动去获取消息， 如果需要手动去获取消息，
 * 那就没有必要使用到ActiveMq了，可以用一个Redis 就足够了。
 * 　　　　不能手动去获取消息，那么我们就可以选择使用一个监听器来监听是否有消息到达，
 * 这样子可以很快的完成对消息的处理。
 * @Date:Created in  21:13 2018/5/4
 * @ModefiedBy:
 */
public class MesageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMessageListener监听到了文本消息：\t"
                    + tm.getText());
            //do something ...
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
