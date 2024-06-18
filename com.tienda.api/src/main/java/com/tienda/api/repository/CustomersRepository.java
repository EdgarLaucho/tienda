package com.tienda.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.api.entity.Customers;

/**
 * Repositorio de clientes heredado de JpaRepository
 */

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
	
	boolean existsByCustomersNameAndCustomerLastName(String customersName, String customerLastName);

}
