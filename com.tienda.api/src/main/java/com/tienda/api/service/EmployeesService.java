package com.tienda.api.service;

import java.util.List;

import com.tienda.api.dto.EmployeesDto;
import com.tienda.api.entity.Employees;
import com.tienda.api.exception.NotFoundException;

public interface EmployeesService {
	
	EmployeesDto findById(Long id) throws NotFoundException;
	List<EmployeesDto> findAll();
	EmployeesDto save(EmployeesDto employeesDto);
	EmployeesDto updateById(EmployeesDto employeesDto);
	EmployeesDto deleteById(Long id);
}
