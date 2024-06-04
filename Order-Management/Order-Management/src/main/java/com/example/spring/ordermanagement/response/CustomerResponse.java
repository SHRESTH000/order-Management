package com.example.spring.ordermanagement.response;

import org.springframework.stereotype.Component;

import com.example.spring.ordermanagement.model.Customer;

import lombok.Data;
import lombok.NoArgsConstructor;
@Component
@Data
@NoArgsConstructor

public class CustomerResponse {

	private Customer data;
	private String message;
	private String code;
	
	/* 
	 * 200 - OK
	 * 401 - unauthorized
	 * 404 - not found
	 * 500 - internalServerError
	 * 400 - BadRequest
	 *   
	 */
}
