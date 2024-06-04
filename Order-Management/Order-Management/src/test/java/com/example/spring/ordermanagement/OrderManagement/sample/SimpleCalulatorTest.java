package com.example.spring.ordermanagement.OrderManagement.sample;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.spring.ordermanagement.sample.SimpleCalculator;

@RunWith(SpringRunner.class) // this class run SimpleCalculator
public class SimpleCalulatorTest {
	
	SimpleCalculator simpleCalculator=new SimpleCalculator();
	
	@Test // it consider this method as a single test case
	public void sumTestPositive() {
		int actualresponse=simpleCalculator.sum(20, 4);
		assertNotNull(actualresponse);
		assertEquals(24,actualresponse);
	}
	
	@Test // it consider this method as a single test case
	public void sumTestNegative() {
		int actualresponse=simpleCalculator.sum(20, 4);
		assertNotNull(actualresponse);
		assertNotEquals(12, actualresponse);
	}
	@Test // it consider this method as a single test case
	public void diffTestPositive() {
		int actualresponse=simpleCalculator.diff(20, 4);
		assertNotNull(actualresponse);
		assertEquals(16,actualresponse);
	}
	
	@Test // it consider this method as a single test case
	public void diffTestNegative() {
		int actualresponse=simpleCalculator.diff(20, 4);
		assertNotNull(actualresponse);
		assertNotEquals(12, actualresponse);
	}
	@Test // it consider this method as a single test case
	public void mulTestPositive() {
		int actualresponse=simpleCalculator.mul(20, 4);
		assertNotNull(actualresponse);
		assertEquals(80,actualresponse);
	}
	
	@Test // it consider this method as a single test case
	public void mulTestNegative() {
		int actualresponse=simpleCalculator.mul(20, 4);
		assertNotNull(actualresponse);
		assertNotEquals(12, actualresponse);
	}
	@Test // it consider this method as a single test case
	public void divTestPositive() {
		int actualresponse=simpleCalculator.div(20, 4);
		assertNotNull(actualresponse);
		assertEquals(5,actualresponse);
	}
	
	@Test // it consider this method as a single test case
	public void divTestNegative() {
		int actualresponse=simpleCalculator.div(20, 4);
		assertNotNull(actualresponse);
		assertNotEquals(12, actualresponse);
	}
	
	

	
}
