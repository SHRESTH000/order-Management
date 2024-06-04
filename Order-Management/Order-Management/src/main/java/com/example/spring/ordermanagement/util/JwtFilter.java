package com.example.spring.ordermanagement.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring.ordermanagement.service.JwtUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Authorization : Bearer qasjdhihjbuis8666-90775cubasunuhe
		
		String TokenHeader=request.getHeader("Authorization");
		String usernameFromToken=null;
		String tokenWithoutBearer=null;
		
		if(TokenHeader !=null && TokenHeader.startsWith("Bearer ")) {
			tokenWithoutBearer=TokenHeader.substring(7);
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
