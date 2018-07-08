package com.fusong.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  18:11 2018/5/4
 * @ModefiedBy:
 */
public class MesageProducer {

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
    /*消息生产者*/
    private static MessageProducer messageProducer;

    public static void main(String[] args) {

        try {
            /*第一步：初始化连接工厂*/
            connectionFactory = new ActiveMQConnectionFactory(UserName, Password, BrokenUrl);
           /*第二步，通过连接工厂获取连接*/
            connection = connectionFactory.createConnection();
            /*第三步，打开链接*/
            connection.start();
            /*第四步，创建session。*/
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            /*第五步，创建消息队列。这个队列的名字相当于一把钥匙。
            * 当消费者想要获取数据时，也是根据这个名字来的*/
            destination = session.createQueue("potqueue");
            /*第六步，创建消息生产者*/
            messageProducer = session.createProducer(destination);
            sendMessage(session, messageProducer);
            /*因为前面设置session的事务为true，所以这里需要提交*/
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
        for (int i = 0; i < 10; i++) {
            /*创建消息文本*/
            TextMessage textMessage = session.createTextMessage(" ActiveMQ 换个玩法 " + i);
            /*消息生产者发送消息*/
            messageProducer.send(textMessage);
        }
    }

}
