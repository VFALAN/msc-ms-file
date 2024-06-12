package com.msc.ms.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "com.msc.ms.file")
@EnableDiscoveryClient
public class MscMsFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscMsFileApplication.class, args);
	}

}
