package com.tienda.api.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "customer_name")
	private String customersName;
	
	@Column(name = "customer_last_name")
	private String customerLastName;
	
	@Column(name = "customer_balance")
	private BigDecimal customerBalance;

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customersName
	 */
	public String getCustomersName() {
		return customersName;
	}

	/**
	 * @param customersName the customersName to set
	 */
	public void setCustomersName(String customersName) {
		this.customersName = customersName;
	}

	/**
	 * @return the customerLastName
	 */
	public String getCustomerLastName() {
		return customerLastName;
	}

	/**
	 * @param customerLastName the customerLastName to set
	 */
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	/**
	 * @return the customerBalance
	 */
	public BigDecimal getCustomerBalance() {
		return customerBalance;
	}

	/**
	 * @param customerBalance the customerBalance to set
	 */
	public void setCustomerBalance(BigDecimal customerBalance) {
		this.customerBalance = customerBalance;
	}

	public Customers(Long customerId, String customersName, String customerLastName, BigDecimal customerBalance) {
		super();
		this.customerId = customerId;
		this.customersName = customersName;
		this.customerLastName = customerLastName;
		this.customerBalance = customerBalance;
	}

	public Customers() {
		super();
		
	}
	
	
	
}
