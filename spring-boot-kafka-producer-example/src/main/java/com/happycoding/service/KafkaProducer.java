package com.happycoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.happycoding.model.Provider;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, Provider> kafkaTemplate;

	@Value("${topic.id}")
	private String topicId;

	public void send(String message, Integer value) {
		kafkaTemplate.send(topicId, new Provider(message, value));
	}
}