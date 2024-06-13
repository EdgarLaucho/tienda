package com.tienda.api.service;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.tienda.api.dto.CustomersDto;
import com.tienda.api.entity.Customers;
import com.tienda.api.exception.NotFoundException;

public interface CustomersService {

	List<CustomersDto> findAll() throws NotFoundException;
	CustomersDto save(CustomersDto custumersDto) throws BadRequestException;
	CustomersDto findById(Long id) throws NotFoundException, BadRequestException;
	CustomersDto update(CustomersDto customersDto)throws NotFoundException,BadRequestException;
	void deleteById(Long id);
}
