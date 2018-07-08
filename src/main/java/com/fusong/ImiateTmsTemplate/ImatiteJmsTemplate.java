package com.fusong.ImiateTmsTemplate;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  9:45 2018/5/6
 * @ModefiedBy:
 */
public class ImatiteJmsTemplate extends JmsTemplate {
    public ImatiteJmsTemplate(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }
}
