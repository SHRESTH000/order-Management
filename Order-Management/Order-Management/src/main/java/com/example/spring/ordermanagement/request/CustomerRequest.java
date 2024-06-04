package com.example.spring.ordermanagement.request;

import lombok.Data;

@Data
public class CustomerRequest {

	private String customerName;
	private String email;
	private String mobile_no;
	private String address;
	private String password;
	
	

}
