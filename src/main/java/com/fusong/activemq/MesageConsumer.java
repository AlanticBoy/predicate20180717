package com.fusong.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  18:43 2018/5/4
 * @ModefiedBy:
 */
public class MesageConsumer {
    /*创建ActiveMQ默认用户名密码和地址*/
    private static final String UserName = ActiveMQConnection.DEFAULT_USER;
    private static final String Password = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BrokenUrl = ActiveMQConnection.DEFAULT_BROKER_URL;
    /*连接工厂*/
    private static ConnectionFactory connectionFactory;
    /*由连接工厂创建连接*/
    private static Connection connection;
    /*会话，接受或者发送消息的线程*/
    private static Session session;
    /*消息的目的地*/
    private static Destination destination;
    /*创建消息消费者*/
    private static MessageConsumer messageConsumer;

    public static void main(String[] args) {
        try {
            // 实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(UserName, Password, BrokenUrl);
         /*第二步，通过工厂获取连接*/
            connection = connectionFactory.createConnection();
         /*第三步，打开链接*/
            connection.start();
            /*第四步，创建会话*/
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            /*第五步，创建目的地*/
            destination = session.createQueue("potqueue");
            /*第六步，创建消费者*/
            messageConsumer = session.createConsumer(destination);
           /*第七步，注册消息监听器*/
            messageConsumer.setMessageListener(new MessListener());
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

    static class MessListener implements MessageListener {

        @Override
        public void onMessage(Message message) {
            try {
                String s = ((TextMessage) message).getText();
                System.out.println(" 得到消息   " + s);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
