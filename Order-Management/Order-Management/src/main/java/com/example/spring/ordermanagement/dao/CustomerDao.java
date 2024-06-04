package com.example.spring.ordermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring.ordermanagement.model.Customer;

@Repository // it is repository interface which does database operation
public interface CustomerDao extends JpaRepository<Customer, Long> {
     // Query= we can write our own customized queries
	@Query(nativeQuery = true,value = "select * from customer where email= :email" )
	public Customer getCustomerByEmail(String email);
    
	/*
	 * Customer  filter for city- select * from om_feb.customer where address=?
	 * 
	 * customer filter for country= select * from om_feb.customer where country=?
	 * */
	@Query(nativeQuery  = true, value="select * from customer where address= :city")
	public List<Customer> getCustomerBasesOnCity(String city);
}
