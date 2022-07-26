package com.bootcamp.yankibootcoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsYankiBootcoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsYankiBootcoinApplication.class, args);
	}

}
