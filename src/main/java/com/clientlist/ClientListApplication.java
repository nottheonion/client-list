package com.clientlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClientListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientListApplication.class, args);
	}

}
