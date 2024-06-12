package com.tienda.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class CustomersDto {

	private Long customerId;
	
	@NotEmpty(message = "El nombre del cliente no puede estar vacío")
	@Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$", message = "El nombre del cliente no puede contener números")
	private String customersName;
	
	
	@NotEmpty(message = "El apellido del cliente no puede estar vacío")
	@Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$", message = "El apellido del cliente no puede contener números")
	private String customerLastName;
	
	@NotNull(message = "El balance del cliente no puede estar vacío")
	@PositiveOrZero(message = "El balance del cliente no puede ser negativo")
	@Digits(integer = 10, fraction = 2, message = "El balance del cliente debe ser un número válido")
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
		this.customersName = customersName.toUpperCase();
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
		this.customerLastName = customerLastName.toUpperCase();
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


	public CustomersDto(Long customerId, String customersName, String customerLastName, BigDecimal customerBalance) {
		super();
		this.customerId = customerId;
		this.customersName = customersName;
		this.customerLastName = customerLastName;
		this.customerBalance = customerBalance;
	}


	public CustomersDto() {
		super();
	}
	
	
}
