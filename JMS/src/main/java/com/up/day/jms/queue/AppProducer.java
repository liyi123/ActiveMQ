package com.up.day.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


public class AppProducer {
	private static final String url = "tcp://127.0.0.1:61616";
	private static final String queueName = "queue-test";
	
	public static void main(String[] args) throws JMSException {
		//1.创建ConnectionFactory
		ConnectionFactory cf = new ActiveMQConnectionFactory(url);
		//2.创建Connection
		Connection connection = cf.createConnection();
		//3.启动连接
		connection.start();
		//4.创建会话：第一个参数表示是否自动提交事务
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建目的地
		Destination destination = session.createQueue(queueName);
		//6.创建生产者
		MessageProducer producer = session.createProducer(destination);
		
		for(int i=0;i<100;i++) {
			//7.创建消息
			TextMessage message = session.createTextMessage("test:"+i);
			//8.发布消息
			producer.send(message);
			System.out.println("发送消息："+message.getText());
		}
		connection.close();
	}
}
