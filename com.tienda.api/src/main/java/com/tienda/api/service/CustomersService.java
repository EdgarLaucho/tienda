package com.tienda.api.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.tienda.api.dto.CustomersDto;
import com.tienda.api.exception.NotFoundException;

public interface CustomersService {

	
	List<CustomersDto> findAll() throws NotFoundException;
	CustomersDto save(CustomersDto custumersDto) throws BadRequestException;
	CustomersDto findById(Long id) throws NotFoundException, BadRequestException;
	CustomersDto update(CustomersDto customersDto)throws NotFoundException,BadRequestException;
    String deleteById(Long id) throws NotFoundException;
    ResponseEntity<InputStreamResource> customersGenerateReport() throws NotFoundException;
}
	