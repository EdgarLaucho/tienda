package com.tienda.api.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tienda.api.dto.CustomersDto;
import com.tienda.api.entity.Customers;
import com.tienda.api.exception.NotFoundException;
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
	private CustomersDto customersDto;
	
	@BeforeEach
	public void setUp() {
		
		customersDto = new CustomersDto();
        customersDto.setCustomerId(1L);
        customersDto.setCustomersName("Edgar");
        customersDto.setCustomerLastName("Laucho");
        customersDto.setCustomerBalance(new BigDecimal("5000.00"));
		
		customers = new Customers();
		customers.setCustomersName("Edgar");
		customers.setCustomerLastName("Laucho");
		customers.setCustomerBalance(new BigDecimal("5000.00"));
		customers.setCustomerId(1L);
	}
	
	@Test
	void customersFindAll() {
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
	void customerSave() throws BadRequestException {
		
		CustomersDto customersDto= new CustomersDto();
		customersDto.setCustomerId(1L);
		customersDto.setCustomersName("Edgar");
		customersDto.setCustomerLastName("Laucho");
		customersDto.setCustomerBalance(new BigDecimal("5000.00"));
		
		when(customersRepository.save(any(Customers.class))).thenReturn(customers);
		
		CustomersDto result = customersServiceImple.save(customersDto);
		assertNotNull(result);
		
		assertEquals("EDGAR", result.getCustomersName());
		assertEquals("LAUCHO", result.getCustomerLastName());
		assertEquals(1L, result.getCustomerId());
		assertEquals(new BigDecimal("5000.00"),result.getCustomerBalance());
		
		verify(customersRepository, times(1)).save(any(Customers.class));
		
	}
	
	@Test
	void customerFindById() throws NotFoundException, BadRequestException {
		 
		when(customersRepository.findById(1L)).thenReturn(Optional.of(customers));

		CustomersDto result = customersServiceImple.findById(1L);
		assertNotNull(result);
		
		assertEquals(1L, result.getCustomerId());	
	}
	
	@Test
	void customerUpdate() throws NotFoundException, BadRequestException {
		when(customersRepository.findById(customersDto.getCustomerId())).thenReturn(Optional.of(customers));
        when(customersRepository.save(customers)).thenReturn(customers);
		
		
		customersDto.setCustomersName("Juan");
		customersDto.setCustomerLastName("Perez");
		customersDto.setCustomerBalance(new BigDecimal("6000.00"));
		
		CustomersDto result= customersServiceImple.update(customersDto);
		
		assertNotNull(result);
		
		assertEquals(customersDto.getCustomerId(), result.getCustomerId());
		assertEquals("JUAN", customers.getCustomersName());
		assertEquals("PEREZ", customers.getCustomerLastName());
		assertEquals(new BigDecimal("6000.00"), customers.getCustomerBalance());
		
		verify(customersRepository, times(1)).findById(customersDto.getCustomerId());
		verify(customersRepository, times(1)).save(customers);
		
	}
	
	@Test
	void customerDelete() {
		when(customersRepository.findById(customersDto.getCustomerId())).thenReturn(Optional.of(customers));
		customersServiceImple.deleteById(customers.getCustomerId());
		
		verify(customersRepository, times(1)).findById(customers.getCustomerId());
		verify(customersRepository, times(1)).delete(customers);
	}



}
