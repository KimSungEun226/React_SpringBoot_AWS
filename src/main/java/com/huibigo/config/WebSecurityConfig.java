package com.huibigo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

import com.huibigo.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http ��ť��Ƽ ����
		http.cors() // WebMvcConfig���� �̹� ���������Ƿ� �⺻ cors ����
			.and()
			.csrf() // csrf�� ���� ������� �����Ƿ� disable
			  .disable()
			.httpBasic()  // token�� ����ϹǷ� basic ���� disable
			  .disable()
			.sessionManagement()  // session ����� �ƴ��� ����
			  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests() // /�� /auth/** ��δ� ���� �� �ص� ��.
			  .antMatchers("/", "/auth/**").permitAll()
			.anyRequest()  // /�� /auth/** �̿��� ��� ��δ� �����ؾߵ�
			  .authenticated();
		
		// filter ���
		// �� ��û����
		// CorsFilter �������Ŀ�
		// jwtAuthenticationFilter �����Ѵ�.
		http.addFilterAfter(jwtAuthenticationFilter,
				CorsFilter.class);
	}

}
