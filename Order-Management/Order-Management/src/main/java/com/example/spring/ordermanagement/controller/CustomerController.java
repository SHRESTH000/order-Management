package com.example.spring.ordermanagement.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.spring.ordermanagement.model.Customer;
import com.example.spring.ordermanagement.request.CustomerRequest;
import com.example.spring.ordermanagement.response.CustomerResponse;
import com.example.spring.ordermanagement.service.CustomerService;

@RestController
@RequestMapping("/om/customer/v1")
public class CustomerController {

	/*
	 * API= Application programming interface - communication between request and
	 * response(entry point where our request starts executing)
	 * 
	 * REST API= Representational state transfer API- is used to handle HTTP web
	 * request
	 *
	 * Method HTTP for API
	 *
	 * post= create/add/insert/save get=find/fetch/read delete=delete
	 * put/patch=update
	 * 
	 * loggers= use to log message of the application in which is use to trace the
	 * the application it maintain the hierarchical history of the application 4
	 * levels of loggers
	 * 
	 * info = to give the information about application warn =to give warning
	 * message error = to display error/exception message debug = to display logs at
	 * debug mode
	 * 
	 * RESTTEMPLET= calling one API inside other API
	 */

	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	RestTemplate restTemplate=new RestTemplate();

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerResponse customerResponse;
	
	
	
	// @RequestBody= it take input from front end in the form of json and passes to
	// the API
	@PostMapping(value = "/save")
	public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest customerRequest) {
		logger.info("cresteCustomer API has started");
		try {
			Customer customer = customerService.saveCustomer(customerRequest);
			logger.info("CUstomer saved successfully and CreateCustomer API ended");
			return ResponseEntity.ok().body(customer);
		} catch (Exception e) {

			logger.error("some error occured while saving customer ");
			return ResponseEntity.internalServerError().body("Customer is not saved");
		}
	}

	@GetMapping(value = "findall")
	public ResponseEntity<?> findAllCustomer() {

		logger.info("FindAllCustomer API has Started");
		try {
			
			List<Customer> customerslist = customerService.getallCustomer();
			logger.info("Customers Found Sucessfully and FindAllCustomer API ended");
			return ResponseEntity.ok().body(customerslist);
		} catch (Exception e) {

			logger.error("Customer Could not be found");
			return ResponseEntity.internalServerError().body("Customer is not found");
		}
	}

	@GetMapping(value = "/countall")
	public ResponseEntity<?> countAllCustomer() {
		logger.info("CountAll API has Started");
		try {
			long count = customerService.countCustomerById();
			logger.info("CountAll API performed its task and ended");
			return ResponseEntity.ok().body(count);
		} catch (Exception e) {

			logger.error("Some error occured during CountAll");
			return ResponseEntity.internalServerError().body("Count is 0");
		}
	}

	@GetMapping(value = "/find/{customerid}")
	public ResponseEntity<?> findCustomerById(@PathVariable("customerid") long customerid) {

		logger.info("findCustomerById API has started");
		try {
			Customer customer = customerService.getCustomerById(customerid);
			customerResponse.setData(customer);
			customerResponse.setMessage("Customer is found");
			customerResponse.setCode(HttpStatus.OK.toString());
			logger.info("Customer found and findCustomerById API has ended");
			return ResponseEntity.ok().body(customerResponse);
			/* return ResponseEntity.ok().body(customer); */

		} catch (Exception e) {
			
            logger.error("customer could not be found");
            customerResponse.setData(null);
            customerResponse.setMessage("Customer not found");
            customerResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return ResponseEntity.internalServerError().body(customerResponse);

			/*
			 * return ResponseEntity.internalServerError().body("The CustomerId " +
			 * customerid + "is not present");
			 */		}
	}

	@PutMapping(value = "/update/{customerid}")
	public ResponseEntity<?> updateCustomer(@PathVariable("customerid") long customerid,
			@RequestBody CustomerRequest customerRequest) {
		logger.info("UpdateCustomerById API has started");
		try {
			Customer customer = customerService.updateCustomer(customerid, customerRequest);
			logger.info("Customer Data is updated and UpdateCustomerById API ended");
			return ResponseEntity.ok().body(customer);
		} catch (Exception e) {
			logger.error("Customer data could not be updated ");
			return ResponseEntity.internalServerError().body("The CustomerId  " + customerid + " can not be updated");
		}

	}

	@DeleteMapping(value = "/delete/{customerid}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customerid") long customerid) {
		logger.info("deleteCustomerById API has started");
		try {
			customerService.deleteCustomerById(customerid);
			logger.info("Customer Data is deleted and deleteCustomerById API ended");
			return ResponseEntity.ok().body("Your Customer is deleted");
		} catch (Exception e) {
            
			logger.error("Customer Data is not deleted some error occured");
			return ResponseEntity.internalServerError().body("Your customer can not be deleted");
		}
	}

	@GetMapping(value = "/getbyemail")
	public ResponseEntity<?> getCustomerByEmail(@RequestParam("email") String email) {
        
		logger.info("getCustomerbyEmail API has started");
		try {
			Customer customer = customerService.getCustomerByEmail(email);
			logger.info("Customer is found and getCustomerBYemail API ended");

			return ResponseEntity.ok().body(customer);
		} catch (Exception e) {
         
			logger.error("Customer is not Found");
			return ResponseEntity.internalServerError()
					.body("The Customer detail with this emailid " + email + " is not Found");
		}

	}

	@GetMapping(value = "/customerbasedoncity")
	public ResponseEntity<?> fingAllCustomerBasedOnCity(@RequestParam("city") String city) {
        
		logger.info("customerBasedonCity API has started");
		try {
			List<Customer> customerslist = customerService.getCustomerBasedOnCity(city);
            logger.info("Customer is found and CustomerBasedonCity API ended");
			return ResponseEntity.ok().body(customerslist);
		} catch (Exception e) {
			logger.error("Customer is not found");
			return ResponseEntity.internalServerError().body("Customer is not found no this address " + city);
		}

	}

	@GetMapping(value = "/findcustomerwithpage")
	public ResponseEntity<?> findCustomerwithpage(@RequestParam("pageno") Integer pageno,
			@RequestParam("pagesize") Integer pagesize) {
        logger.info("findCustomerwithpage API has stared");
		try {
			List<Customer> customerslist = customerService.getallCustomerPage(pageno, pagesize);
            logger.info("Customer page is found and findCustomerwithPage API ended");
			return ResponseEntity.ok().body(customerslist);
		} catch (Exception e) {
            
			logger.error("some error Occured during Find Customer page ");
			return ResponseEntity.internalServerError().body("Customer page is not found");
		}

	}
	


	
	// REST Template using  REST API for get operation
	@GetMapping(value = "/findusadata")
	public ResponseEntity<?> getusadata() {
		HttpHeaders headers=new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		
		HttpEntity<String> httpEntity=new HttpEntity<>(headers);
		
		ResponseEntity<?> responseEntity=restTemplate.exchange("https://datausa.io/api/data?drilldowns=Nation&measures=Population", HttpMethod.GET, httpEntity, String.class);
		
		return responseEntity;
	}
	
	@GetMapping(value = "/findallcustomersresttemplate")
	public ResponseEntity<?> findAllCustomersUsingRestTemplate() {
		logger.info("findAllCustomersUsingRestTemplate has started");
		try {
			HttpHeaders headers=new HttpHeaders();
			headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
			
			HttpEntity<?> httpEntity=new HttpEntity<>(headers);
			ResponseEntity< ?> responseEntity=restTemplate.exchange("http://localhost:7777/om/customer/v1/findall", HttpMethod.GET, httpEntity, List.class);
			return responseEntity;
			
		} catch (Exception e) {
            
			logger.error("some error occur and customer is not found");
			return ResponseEntity.internalServerError().body("Customer is not found");
					
		}
		
	}
	@GetMapping(value = "/findallcustomersresttemplate2")
	public ResponseEntity<?> findAllCustomersUsingRestTemplate2() {
		logger.info("findAllCustomersUsingRestTemplate2 has started");
		try {
			ResponseEntity<?> responseEntity=restTemplate.getForEntity("http://localhost:7777/om/customer/v1/findall", List.class);
			
			logger.info("Customer is found and findAllCustomersUsingRestTemplate2 ended");
			return responseEntity;
		} catch (Exception e) {
			
			logger.error("Customer is not found");
			return ResponseEntity.internalServerError().body("Some error occur and customer is not found");

		
		}
	}
	
	@GetMapping(value = "/findusadata2")
	public ResponseEntity<?> getUsaData2() {
		logger.info("getUsaData resttemplate API has started");
		try {
			ResponseEntity<?> responseEntity=restTemplate.getForEntity("https://datausa.io/api/data?drilldowns=Nation&measures=Population", String.class);
			logger.info("Data is found and getusadata api ended");
			return responseEntity;
		} catch (Exception e) {
            
			logger.error("data is not Found by restapi");
			return ResponseEntity.internalServerError().body("Some error occur and data is not found by RestTemplate");
		
		}
	}
	
	@PostMapping(value = "savecustomersresttemplate")
	public ResponseEntity<?> saveCustomersUsingRestTemplate(@RequestBody CustomerRequest customerRequest) {
		
		logger.info("saveCustomersUsingRestTemplate has started");
		try {
			HttpHeaders headers=new HttpHeaders();
			headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
			HttpEntity<?> httpEntity=new HttpEntity<>(customerRequest,headers);
		    
			ResponseEntity<?> responseEntity=restTemplate.exchange("http://localhost:7777/om/customer/v1/save", HttpMethod.POST, httpEntity, String.class);
			
			logger.info("Customer data is saved and saveCustomersUsingRestTemplate ended");
			return responseEntity;
		} catch (Exception e) {

		    logger.error("some error occur in saveCustomersUsingRestTemplate ");
			return ResponseEntity.internalServerError().body("Some error occur");
		}
	}
	
	@PostMapping(value = "/savecustomersresttemplate2")
public ResponseEntity<?> saveCustomersUsingRestTemplate2(@RequestBody CustomerRequest customerRequest) {
		
		logger.info("saveCustomersUsingRestTemplate2 has started");
		try {
			HttpEntity<?> httpEntity=new HttpEntity<>(customerRequest);
		    
			ResponseEntity<?> responseEntity=restTemplate.postForEntity("http://localhost:7777/om/customer/v1/save", httpEntity, String.class);
			
			logger.info("Customer data is saved and saveCustomersUsingRestTemplate2 ended");
			return responseEntity;
		} catch (Exception e) {

		    logger.error("some error occur in saveCustomersUsingRestTemplate2 ");
			return ResponseEntity.internalServerError().body("Some error occur");
		}
	} 

}
