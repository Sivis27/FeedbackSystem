package com.fms.eurekadiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringBootEurekaDiscovery {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEurekaDiscovery.class, args);
	}

}
