package com.hyxy.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Service;

@Service
//@RabbitListener(queues = "shopping_banner")
public class ItemAddRabbitService2 {

	//消费者
	//点对点(简单队列)
	@RabbitHandler
	public void add(String string) {
		System.out.println("接收信息2："+string);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
