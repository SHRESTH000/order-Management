package com.example.spring.ordermanagement.util;

import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable {

	// define expirationo time of token
	private static final long TOKEN_VALIDATITY = 2 * 60 * 60 * 1000;

	// take secret value from application.properties and assign it to variable

	@Value("${secret}")
	private String jwtSecret;

	public String generateJwtToken(UserDetails userDetails) {
		Map<String, Object> claim = new HashMap<>();
		return Jwts.builder().setClaims(claim).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDATITY))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	// validate Jwt token
	// validate the username from the token and username from userdetail
	public Boolean validateJwtToken(String token,UserDetails userDetails) {
		String tokenUsernameString=getUsernameFromToken(token);
		//we need to get expiration time
		final Claims claims=Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		Boolean isTokenExpired= claims.getExpiration().before(new Date());
		
		if(tokenUsernameString.equals(userDetails.getUsername()) && !isTokenExpired) {
			return true; // valid token
		}
		else {
			return false; // invaild token
		}
		
	}
	
	
	
	//get username from jwt token
	public String getUsernameFromToken(String Token) {
		final Claims claims=Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(Token).getBody();
		return claims.getSubject();// subject contain username
	}
}
