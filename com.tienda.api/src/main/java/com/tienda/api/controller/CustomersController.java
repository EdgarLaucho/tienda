package com.tienda.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.api.dto.CustomersDto;
import com.tienda.api.exception.NotFoundException;
import com.tienda.api.service.CustomersService;

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

}
