package com.huibigo.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.huibigo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
	private static final String SECRET_KEY = "ASDFJdafjvfdsDFgsd4SFDHnSDNGincsd1DF"
			+ "nasdDSV3JFGbnjksdnui3opASDFSDVnjkfdfSDFnjbkdffvdcd3v4XCJVDccx2FM5FDBFGD";
	
	public String create(UserEntity userEntity) {
		// ���� �������κ��� 1�Ϸ� ����
		Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
		
		// JWT Token����
		return Jwts.builder()
				// header�� �� ���� �� ������ �ϱ� ���� SECRET_KEY
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				// payload�� �� ����
				.setSubject(userEntity.getId()) // sub
				.setIssuer("demo app") //iss
				.setIssuedAt(new Date()) // iat
				.setExpiration(expiryDate) // exp
				.compact();
	}
	
	public String validateAndGetUserId(String token) {
		// parseClaimsJws �޼��尡 Base 64�� ���ڵ� �� �Ľ�
		// ��, ����� ���̷ε带 setSigningKey�� �Ѿ�� ��ũ���� �̿��� ���� ��, token�� ����� ��.
		// �������� �ʾҴٸ� ���̷ε�(Claims) ����, ������� ���ܸ� ����
		// �� �� �츮�� userId�� �ʿ��ϹǷ� getBody�� �θ���.
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	

}
