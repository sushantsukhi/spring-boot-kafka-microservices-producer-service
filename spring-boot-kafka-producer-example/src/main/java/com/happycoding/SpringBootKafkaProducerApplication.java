package com.happycoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.happycoding.service.MonitorDirectory;

@SpringBootApplication
public class SpringBootKafkaProducerApplication implements CommandLineRunner {

	@Autowired
	MonitorDirectory monitorDirectory;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("SpringBootKafkaProducerApplication::run started..");
		monitorDirectory.watchDirectory();
		System.out.println("SpringBootKafkaProducerApplication::run started..");
	}
}
