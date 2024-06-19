package com.tienda.api.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.api.dto.CustomersDto;
import com.tienda.api.exception.NotFoundException;
import com.tienda.api.service.CustomersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class CustomersController {

	private final CustomersService customersService;

    @Autowired
    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

	@GetMapping
	public List<CustomersDto> findAll() throws NotFoundException {
		return customersService.findAll();
	}
	
	@GetMapping("/busqueda/{id}")
	public CustomersDto findByid(@PathVariable CustomersDto customersDto) throws NotFoundException, BadRequestException {
		return customersService.findById(customersDto.getCustomerId());
	}

	@PostMapping("/guardar")
	public  CustomersDto save(@RequestBody @Valid CustomersDto customersDto) throws BadRequestException {
		return customersService.save(customersDto);
	}
	
	@PutMapping("/actualizar")
	public CustomersDto update(@RequestBody CustomersDto customersDto) throws NotFoundException, BadRequestException {
		return customersService.update(customersDto);
	}
	
	@DeleteMapping("/borrar/{id}")
	public String deleteById(@PathVariable Long id) {
		return customersService.deleteById(id);
	}
	
	@GetMapping("/pdf/clientes")
	public ResponseEntity<InputStreamResource> customersGenerateReport(){
		return customersService.customersGenerateReport();
	}
}
