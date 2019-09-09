package com.hyxy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	// 声明队列的名称
	private static final String QUEUE_NAME = "shopping_banner";
	// 交换机名称
	private final static String exchange = "fanout_exchange";

	// 创建队列
	@Bean
	public Queue queue() {
		return new Queue(RabbitMQConfig.QUEUE_NAME);
	}

	// 创建一个fanout类型的交换机,fanout--不处理路由键   
	@Bean
	DirectExchange directExchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	TopicExchange topicexchange() {
		return new TopicExchange(exchange);
	}

	// 交换机绑定队列
	@Bean
	Binding bindingExchangeA() {
//		return BindingBuilder.bind(queue()).to(fanoutExchange());
		return BindingBuilder.bind(queue()).to(directExchange()).with("inter");
	}
}
