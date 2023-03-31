package com.pws.admin;

import com.pws.admin.utility.AuditAwareImpl;
import com.pws.admin.websocket.MyWebSocketClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition
@EnableJpaAuditing
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.pws.admin*" })
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditAwareImpl();
	}




}
