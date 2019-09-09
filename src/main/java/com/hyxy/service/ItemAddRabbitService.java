package com.hyxy.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "shopping_banner")
public class ItemAddRabbitService {

	@Resource
	private JieshaoService JieshaoService;
	// 消费者
	// 点对点(简单队列)
	@RabbitHandler
	public void add(String string) {
		System.out.println("接收信息1：" + string);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@RabbitHandler
	public void add(Map<String, Object> map) {
//		String id = (String) map.get("id");
//		if (id==null || "".equals(id)) {
//			JieshaoService.updataJS(map);
//		}else {
			JieshaoService.addIncreased(map);
//		}
//			
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
