package com.example.spring.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.spring.ordermanagement.dao.CustomerDao;
import com.example.spring.ordermanagement.model.Customer;
import com.example.spring.ordermanagement.request.CustomerRequest;

@Service
public class CustomerService {

	Logger logger=LoggerFactory.getLogger(CustomerService.class);
	@Autowired
	private CustomerDao customerDao;
	
	public Customer saveCustomer(CustomerRequest inputCustomerRequest) {

		// create customer object
		// get inputCustomerRequest and set it into Customer object
		// save customer object

		logger.info("savecustomer method started");
		Customer customer = new Customer();

		customer.setCustomerName(inputCustomerRequest.getCustomerName());
		customer.setEmail(inputCustomerRequest.getEmail());
		customer.setMobileNo(inputCustomerRequest.getMobile_no());
		customer.setAddress(inputCustomerRequest.getAddress());
		customer.setPassword(inputCustomerRequest.getPassword());

		Customer savedCustomer = customerDao.save(customer);
		if (savedCustomer == null) {
			logger.error("some error occured during saving Customer ");
			throw new RuntimeException("customer is not saved");
		}

		logger.info("savecustomer method ended");
		return savedCustomer;
	}

	public List<Customer> getallCustomer() {
		
        logger.info("getallcustomer method started");
		List<Customer> customerslist = customerDao.findAll();

		if (customerslist.isEmpty()) {
            logger.error("Customer is not  present in database");
			throw new RuntimeException("no Customer is found");
		}

		
		logger.info("getallCustomer method ended");
		return customerslist;
	}

	public long countCustomerById() {

		logger.info("countCustomerById method Started");
		long count = customerDao.count();
		if (count == 0) {

			logger.error("some error occured or maybe there is no customer in database");
			throw new RuntimeException("There is no Customer");
		}

		logger.info("CountCustomerById method ended");
		return count;
	}

	public Customer getCustomerById(long customerId) {
		
        logger.info("getCustomerById method started");
		Optional<Customer> customerOptional = customerDao.findById(customerId);

		if(customerOptional.isEmpty()) {
			logger.error("Customer is not present ");
			throw new RuntimeException("Customer is not present");
		}
		Customer customerfromDatabase = customerOptional.get();

		if (customerfromDatabase == null) {
			logger.error("Customer not present in database");
			throw new RuntimeException("Customer is not found");
		}
		
		logger.info("getCustomerById method ended");
		return customerfromDatabase;
	}

	public Customer updateCustomer(long customerId, CustomerRequest newCustomerRequest) {

		// find weather the customer is present or not

		logger.info("updateCustomer method started");
		Customer updateCustomer = null;
		Customer oldCustomer = getCustomerById(customerId);
		if (oldCustomer != null) {
			oldCustomer.setCustomerName(newCustomerRequest.getCustomerName());
			oldCustomer.setEmail(newCustomerRequest.getEmail());
			oldCustomer.setMobileNo(newCustomerRequest.getMobile_no());
			oldCustomer.setAddress(newCustomerRequest.getAddress());
			oldCustomer.setPassword(newCustomerRequest.getPassword());

			updateCustomer = customerDao.save(oldCustomer);

			if (updateCustomer == null) {

				logger.error("Customer Data is not updated");
				throw new RuntimeException("Customer is not Updated");
			}
		}
		logger.info("updateCustomer method ended");
		return updateCustomer;

	}

	public void deleteCustomerById(long customerId) {

		logger.info("deleteCustomerById method started");
		customerDao.deleteById(customerId);
        logger.info("deleteCustomerById method ended");
		
	}

	public Customer getCustomerByEmail(String email) {

		logger.info("getCustomerById method started");
		Customer customer = customerDao.getCustomerByEmail(email);
		
		if(customer == null) {
			logger.error("Customer is not present in database");
			throw new RuntimeException("Customer is not Found");
		}

		logger.info("getCustomerById method ended");
		return customer;

	}

	public List<Customer> getCustomerBasedOnCity(String city) {
		
        logger.info("getCustomerBasedonCity method started");
		List<Customer> customerslist = customerDao.getCustomerBasesOnCity(city);
		
		if(customerslist.isEmpty()) {
			
			logger.error("Customer is not present ,and some error occur");
			throw new RuntimeException("Customer is not found");
		}
        
		logger.info("getCustomerBasesOnCity method ended");
		return customerslist;

	}
	/*
	 * pagination = fetching records or data based on pages two inputs = page number
	 * we want to see and page size
	 * 
	 * total record-37 page size-10 0th page -1-10 1st page -11-20 2nd page -21-30
	 * 3rd page -31-37
	 * 
	 * sorting = arranging the record based on specific field in either ascending or
	 * descending order
	 * 
	 */

	public List<Customer> getallCustomerPage(Integer pageNumber, Integer pageSize) {

		// Page<Customer> customerpage=customerDao.findAll(PageRequest.of(pageNumber,
		// pageSize));
		// we need to convert customerpage to list of customer

		logger.info("getallCustomerPage method started");
		Page<Customer> customerpage = customerDao.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("customerName").ascending()));
		
		if(customerpage.isEmpty()) {
			 
			logger.error("Customer page not found");
			throw new RuntimeException("Customerpage is not avaiable");
		}
		
		List<Customer> customerlist = new ArrayList<>();

		for (Customer customer : customerpage) {

			customerlist.add(customer);
		}

		logger.info("getallCustomerpage method ended");
		return customerlist;
	}
	 
}
