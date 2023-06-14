package com.huibigo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final long MAX_AGE_SECS = 3600;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// ��� ��ο� ����
		registry.addMapping("/**")
		// Origin�� http:localhost:3000�� ����.
		.allowedOrigins("http://localhost:3000")
		// GET, POST, PUT, PATCH, DELETE, OPTION �޼��带 ����Ѵ�.
		.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
		.allowedHeaders("*")
		.allowCredentials(true)
		.maxAge(MAX_AGE_SECS);
	}

}
