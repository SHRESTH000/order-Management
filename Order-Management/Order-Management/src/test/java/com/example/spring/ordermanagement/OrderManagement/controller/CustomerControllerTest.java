package com.example.spring.ordermanagement.OrderManagement.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.mockito.MockitoAnnotations;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.ordermanagement.controller.CustomerController;
import com.example.spring.ordermanagement.model.Customer;
import com.example.spring.ordermanagement.request.CustomerRequest;
import com.example.spring.ordermanagement.service.CustomerService;


@RunWith(SpringRunner.class)
public class CustomerControllerTest {

	@InjectMocks
	private CustomerController customerController;
	
	@Mock
	private  CustomerService customerService;
	
	@BeforeEach // before running each and every test case this set up method gets called
	public void SetUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	// this is request payload
	private CustomerRequest getCustomerRequest() {
		CustomerRequest customerRequest=new CustomerRequest();
		customerRequest.setCustomerName("test");
		customerRequest.setEmail("test12@gmail.com");
		customerRequest.setMobile_no("93827383737");
		customerRequest.setAddress("233,djnbd,bdx,hshz");
		customerRequest.setPassword("test@123");
		return customerRequest;
	}
	
	// this is response
	private Customer getCustomer() {
		
		Customer customer=new Customer();
		customer.setCustomerId(124);
		customer.setCustomerName("test");
		customer.setEmail("test12@gmail.com");
		customer.setMobileNo("93827383737");
		customer.setAddress("233,djnbd,bdx,hshz");
		customer.setPassword("test@123");
		return customer;
	}
	@Test
	public void createCustomerTestPostive() {
		Mockito.when(customerService.saveCustomer(Mockito.any())).thenReturn(getCustomer());
		ResponseEntity<?> actualresponse=customerController.createCustomer(getCustomerRequest());
		assertNotNull(actualresponse);
		assertEquals(getCustomer(), actualresponse.getBody());
	}
	@Test
	public void createCustomerTestNegative() {
		Mockito.when(customerService.saveCustomer(Mockito.any())).thenThrow(new RuntimeException());
		ResponseEntity<?> actualresponse=customerController.createCustomer(getCustomerRequest());
		assertNotNull(actualresponse);
		assertEquals("Customer could not be saved", actualresponse.getBody());
	}
	@Test
	public void findAllCustomerTestPostive() {
		List<Customer> customerlist=new ArrayList<>();
		customerlist.add(getCustomer());
		Mockito.when(customerService.getallCustomer()).thenReturn(customerlist);
		ResponseEntity<?> actualresponse=customerController.findAllCustomer();
		assertNotNull(actualresponse);
		assertEquals(customerlist, actualresponse.getBody());
	}
	@Test
	public void findAllCustomerTestNegative() {
		Mockito.when(customerService.getallCustomer()).thenThrow(new RuntimeException());
		ResponseEntity<?> actualresponse=customerController.findAllCustomer();
		assertNotNull(actualresponse);
		assertEquals("customer is not found", actualresponse.getBody());
	}
	
	
	
	
	
}
