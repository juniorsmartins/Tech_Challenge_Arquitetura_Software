package com.techchallenge.devnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // Spring Cloud OpenFeign - permite uso de comunicação síncrona
@SpringBootApplication
public class DevnetApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevnetApplication.class, args);
	}
}

