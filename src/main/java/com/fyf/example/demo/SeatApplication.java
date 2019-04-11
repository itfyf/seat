package com.fyf.example.demo;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeatApplication.class, args);
	}

	
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return new ConfigurationCustomizer() {
			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration) {
				configuration.setMapUnderscoreToCamelCase(true);// 设置驼峰命名规则
			}
		};
	}
}
