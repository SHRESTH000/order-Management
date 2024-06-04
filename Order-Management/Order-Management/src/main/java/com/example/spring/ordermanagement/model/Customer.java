package com.example.spring.ordermanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // it's refer to this class is model or entity class
@Table(name="customer") // it will create a table in db with name 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@Column(name="customer_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customerId;
	
	@Column(name="customer_Name" , nullable = false)
	private String customerName;
	
	@Column(name="email" , nullable = false ,unique = true)
	private String email;
	
	@Column(name="mobile_No" , nullable = false,unique = true)
	private String mobileNo;
	
	@Column(name="address" , nullable = false)
	private String address;
	
	@Column(name="password",nullable = false)
	private String password;
	
}
