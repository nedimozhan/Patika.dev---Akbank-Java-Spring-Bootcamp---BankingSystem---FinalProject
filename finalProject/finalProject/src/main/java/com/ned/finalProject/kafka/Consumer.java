package com.ned.finalProject.kafka;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ned.finalProject.log.AbstractLog;

@Component
public class Consumer {

	private static final Logger logger = Logger.getLogger(Consumer.class);

	@KafkaListener(topics = "logs", groupId = "transfer_consumer_group")
	public void listenTransfer(@Payload AbstractLog log, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {

		logger.info(log.getLogMessage());

	}
}