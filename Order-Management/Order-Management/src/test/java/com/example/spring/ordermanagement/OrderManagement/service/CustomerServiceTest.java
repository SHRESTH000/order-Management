package com.example.spring.ordermanagement.OrderManagement.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockitoSession;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.ordermanagement.dao.CustomerDao;
import com.example.spring.ordermanagement.model.Customer;
import com.example.spring.ordermanagement.request.CustomerRequest;
import com.example.spring.ordermanagement.service.CustomerService;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerDao customerDao;
	
	@BeforeEach 
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
		public void saveCustomerTestPositive() {
			Mockito.when(customerDao.save(Mockito.any())).thenReturn(getCustomer());
			Customer actualresponse=customerService.saveCustomer(getCustomerRequest());
			assertNotNull(actualresponse);
			assertEquals(getCustomer(), actualresponse);
		}
		@Test
		public void saveCustomerTestNegative() {
			Mockito.when(customerDao.save(Mockito.any())).thenReturn(null);
			assertThrows(RuntimeException.class, ()-> customerService.saveCustomer(getCustomerRequest()));
		}
		
		@Test
		public void getAllCustomerTestPostive() {
			List<Customer> customerlist=new ArrayList<>();
			customerlist.add(getCustomer());
			Mockito.when(customerDao.findAll()).thenReturn(customerlist);
			List<Customer> actualresponse=customerService.getallCustomer();
			assertNotNull(actualresponse);
			assertEquals(customerlist, actualresponse);
			
		}
		
		@Test
		public void getAllCustomerTestNegative() {
			List<Customer> customerlist=new ArrayList<>();
			Mockito.when(customerDao.findAll()).thenReturn(customerlist);
			assertThrows(RuntimeException.class,() -> customerService.getallCustomer()); 
		}
		
		
	
}
