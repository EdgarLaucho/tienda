package com.tienda.api.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.tienda.api.dto.ProductDto;
import com.tienda.api.exception.NotFoundException;

public interface ProductService {

	List<ProductDto> findAll() throws NotFoundException;
	ProductDto save(ProductDto productDto) throws BadRequestException;
	ProductDto findById(Long id) throws NotFoundException, BadRequestException;
	ProductDto update(ProductDto productDto)throws NotFoundException,BadRequestException;
    String deleteById(Long id) throws NotFoundException;
    ResponseEntity<InputStreamResource> customersGenerateReport();
}
