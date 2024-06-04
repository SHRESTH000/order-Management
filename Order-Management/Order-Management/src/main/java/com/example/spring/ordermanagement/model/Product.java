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

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@Column(name="product_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	
	@Column(name="product_Name", nullable = false)
	private String productName;
	
	@Column(name="price", nullable = false)
	private String price;
	
	@Column(name="product_Quantity", nullable = false)
	private String productQuantity;
	
	@Column(name="product_Description", nullable = false)
	private String product_description;
}
