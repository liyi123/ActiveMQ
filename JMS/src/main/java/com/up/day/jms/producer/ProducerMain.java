package com.up.day.jms.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("producer.xml");
		ProducerService service = ctx.getBean("producer",ProducerService.class);
		for(int i=0;i<100;i++) {
			service.sendMessage("test:"+i);
		}
		ctx.close();
	}
}
