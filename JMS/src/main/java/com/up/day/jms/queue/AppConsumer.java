package com.up.day.jms.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppConsumer {
	private static final String url = "tcp://127.0.0.1:61616";
	private static final String queueName = "queue-test";
	
	public static void main(String[] args) throws JMSException {
		//1.创建ConnectionFactory
		ConnectionFactory cf = new ActiveMQConnectionFactory(url);
		//2.创建Connection
		Connection connection = cf.createConnection();
		//3.启动连接
		connection.start();
		//4.创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建目的地
		Destination destination = session.createQueue(queueName);
		//6.创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		
		//7.创建一个监听器
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage tMessage = (TextMessage)message;
				try {
					System.out.println("接收到消息："+tMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
//		connection.close();
	}
}
