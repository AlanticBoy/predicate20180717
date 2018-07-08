package com.fusong.controller;

import com.fusong.ActiveQueue.ConsumerService;
import com.fusong.ActiveQueue.ProducerService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:付风松
 * @Description:在测试的时候记着开，ActiveMQ服务器
 * @Date:Created in  20:54 2018/5/4
 * @ModefiedBy:
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private Destination destination;

    //队列消息生产者
    @Autowired
    private ProducerService producer;

    //队列消息消费者
    @Autowired
    private ConsumerService consumer;


    @RequestMapping("/found")
    public void print(HttpServletResponse response) throws IOException {
        response.getWriter().write("hello");
    }

    @RequestMapping(value = "/SendMessage", method = {RequestMethod.GET, RequestMethod.POST})
    public void send(HttpServletRequest request, @RequestParam(defaultValue = "hello,baby") String mesg) {
        String message = "le vista ,baby";
        System.out.println(" has enter way  ");
        producer.sendMessage(mesg);
    }

    @RequestMapping(value = "/ReceiveMessage", method = {RequestMethod.GET, RequestMethod.POST})
    public void receive(HttpServletResponse response) throws JMSException, IOException {
        TextMessage tm = consumer.receive(destination);
        JSONObject object = new JSONObject();
        object.accumulate("text", tm.getText());
        response.getWriter().write(object.toString());
    }
}
