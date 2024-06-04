package com.example.spring.ordermanagement.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	
	@Id
	@Column(name="order_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	
	@Column(name="pro_pur_quan",nullable = false)
	private String  productPurchaseQuantity;
	
	@Column(name="product_total_price",nullable = false)
	private String productTotalPrice;
	
	@Column(name="order_status",nullable = false)
	private String orderStatus;
	
	@Column(name="delivery_address",nullable = false)
	private String deliveryAddress;
	
	@Column(name="payment_method",nullable = false)
	private String paymentMethod;
	
	
	// join Column= name should be same as primary key name of customer and product table
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id", nullable = false)
	private Customer customer;
    
    // Eager= fetches data just be id
    //lazy= won't fetch any data
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id",nullable = false)
	private Product product;
	
}
