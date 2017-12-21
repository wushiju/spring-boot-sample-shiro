package com.example.springbootsampleshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.springbootsampleshiro.mapper")
public class SpringBootSampleShiroApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringBootSampleShiroApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleShiroApplication.class, args);
		logger.info("SpringBoot Start Success");
	}
}
