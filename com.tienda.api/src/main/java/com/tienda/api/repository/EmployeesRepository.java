package com.tienda.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.api.entity.Employees;

/**
 * Repositorio de empleados heredado de JpaRepository
 */
@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long>{

}
