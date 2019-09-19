package com.happycoding.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.happycoding.model.Provider;

@Configuration
public class KafkaProducerConfiguration {
	
	@Value("${bootstrap.servers}")
	private String bootstrapServers;
	
	@Bean
	public ProducerFactory<String, Provider> producerFactory() {
		Map<String, Object> configProps = new HashMap<String, Object>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<String, Provider>(configProps);
	}

	@Bean
	public KafkaTemplate<String, Provider> kafkaTemplate() {
		return new KafkaTemplate<String, Provider>(producerFactory());
	}

}
