package com.happycoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happycoding.service.KafkaProducer;
import com.happycoding.service.WordReader;

@RestController
public class ProviderResource {

	@Autowired
	KafkaProducer kafkaProducer;

	@Autowired
	WordReader wordReader;

	@GetMapping(value = "kafka-example")
	public String producer(@RequestParam("value") int value) {
		int fileNo = value % 5;
		String message = wordReader.processWordDoc(fileNo);
		kafkaProducer.send(message, value);
		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}

}