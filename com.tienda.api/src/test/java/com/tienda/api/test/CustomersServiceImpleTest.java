package com.tienda.api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tienda.api.dto.CustomersDto;
import com.tienda.api.entity.Customers;
import com.tienda.api.repository.CustomersRepository;
import com.tienda.api.service.implement.CustomersServiceImple;

import ch.qos.logback.classic.Logger;

@ExtendWith(MockitoExtension.class)
public class CustomersServiceImpleTest {
	
	
	/**
	 * Clase emuladora de repositorio
	 */
	@Mock
	private CustomersRepository customersRepository;
	
	@Mock
	private Logger logger;
	
	@InjectMocks
	private CustomersServiceImple customersServiceImple;
	
	private Customers customers;
	
	@BeforeEach
	public void setUp() {
		
		customers = new Customers();
		customers.setCustomersName("Edgar");
		customers.setCustomerLastName("Laucho");
		customers.setCustomerBalance(new BigDecimal("5000.00"));
		customers.setCustomerId(1L);
	}
	
	@Test
	void findAllCustomers() {
	    // Configurar el comportamiento del mock del repositorio de clientes
	    when(customersRepository.findAll()).thenReturn(Arrays.asList(customers));

	    // Ejecutar el método que se está probando
	    List<CustomersDto> customersDtoList = customersServiceImple.findAll();

	    // Verificar que la lista devuelta no sea nula
	    assertNotNull(customersDtoList);

	    // Verificar el tamaño de la lista
	    assertEquals(1, customersDtoList.size());

	    // Verificar el contenido de la lista
	    CustomersDto customersDto = customersDtoList.get(0);
	    assertEquals(1L, customersDto.getCustomerId());
	    assertEquals("EDGAR", customersDto.getCustomersName());
	    assertEquals("LAUCHO", customersDto.getCustomerLastName());
	    assertEquals(new BigDecimal("5000.00"), customersDto.getCustomerBalance());
	}
	
	@Test
	void saveCustomers() throws BadRequestException {
		when(customersRepository.save(any(Customers.class))).thenReturn(customers);
		
		assertNotNull(customersServiceImple.save(new CustomersDto()));
		
		
	}



}
