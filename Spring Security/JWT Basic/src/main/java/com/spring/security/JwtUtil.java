package com.spring.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	public static void main(String[] args) {
		//1. generate token
		String secret = "SECRET";

		//xxx.yyy.zzzz
		String token = 
				Jwts.builder()
				.setId("123456") //userid
				.setSubject("ajay") //username
				.setIssuer("MYAPP") //JWT provider
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)))
				.signWith(SignatureAlgorithm.HS256, secret.getBytes())
				.compact();

		//String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTYiLCJzdWIiOiJhamF5IiwiaXNzIjoiTklULUhZRCIsImlhdCI6MTY0MDMxNzUwMCwiZXhwIjoxNjQwMzE3NTYwfQ.d88xnSpWaLeUvnl-IAbV8q8a2NpNqk6O9KpLMfw8Z0g";
		System.out.println(token);

		Claims claims = 
				Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
		
		System.out.println(claims.getId());
		System.out.println(claims.getSubject());
		System.out.println(claims.getIssuer());

	}
}