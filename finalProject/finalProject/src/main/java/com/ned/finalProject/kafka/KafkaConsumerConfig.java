package com.ned.finalProject.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ned.finalProject.log.AbstractLog;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, AbstractLog> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer_group_one");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.ned.finalProject.log");
		return new DefaultKafkaConsumerFactory<String, AbstractLog>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, AbstractLog> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, AbstractLog> factory = new ConcurrentKafkaListenerContainerFactory<String, AbstractLog>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}