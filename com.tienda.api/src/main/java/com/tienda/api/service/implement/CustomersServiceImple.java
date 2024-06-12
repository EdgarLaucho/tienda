package com.tienda.api.service.implement;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.api.dto.CustomersDto;
import com.tienda.api.entity.Customers;
import com.tienda.api.exception.NotFoundException;
import com.tienda.api.repository.CustomersRepository;
import com.tienda.api.service.CustomersService;

@Service
public class CustomersServiceImple implements CustomersService {

	/**
	 * Instancia de logger para trazas.
	 */
	private static final Logger logger = LoggerFactory.getLogger(CustomersServiceImple.class);

	/**
	 * Instancia de objectMapper convertidor de dto a entity y viseversa
	 */
	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Instancia del Repositorio
	 */
	private final CustomersRepository customersRepository;

	public CustomersServiceImple(CustomersRepository customersRepository) {
		super();
		this.customersRepository = customersRepository;
	}

	@Override
	public List<CustomersDto> findAll() throws NotFoundException{
		if (logger.isDebugEnabled()) {
			logger.debug("Entrando en el metodo findAll de CustomersCerviceImple");
		}
		try {
			List<Customers> customers = customersRepository.findAll();
			if (customers.isEmpty()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Lista de clientes vacia");
				}
				throw new NotFoundException("No hay clientes registrados");
			} else {
				return convertListEntityToListDTO(customers);
			}

		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Ha ocurrido un error inesperado");
			}
			throw e;
		}
	}

	@Override
	public CustomersDto save(CustomersDto customersDto) throws BadRequestException{
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Entrando al metodo CustomersDto de la clase CustomersServiceImple");
			}
			if (customersDto == null) {
				if (logger.isErrorEnabled()) {
					logger.error("No hay datos a guardar");
				}
				throw new BadRequestException("Los datos estan vacios");
			}else {
				Customers customers= convertDTOToEntity(customersDto);
				return convertEntityToDTO(customersRepository.save(customers));
			}
		} catch (BadRequestException e) {
			throw e;
		}catch (Exception i) {
			if (logger.isErrorEnabled()) {
				logger.error("Error desconocido");
			}
			throw i;
		}
		
		
		
	}

	@Override
	public CustomersDto findById(Long id) {
		return null;
	}

	@Override
	public CustomersDto update(CustomersDto customersDto) {
		return null;
	}

	@Override
	public void deleteById(Long id) {
	}

	
	/**
	 * Metodo convertidos lista entidad a lista dto
	 * 
	 * @param employees :Entidad del sistema
	 * @return Lista de empleados
	 */
	private List<CustomersDto> convertListEntityToListDTO(List<Customers> customers) {
		return customers.stream().map(customer -> objectMapper.convertValue(customer, CustomersDto.class)).toList();
	}

	/**
	 * Metodo convertidor de dto a entidad
	 */
	private static Customers convertDTOToEntity(CustomersDto customersDto) {
		return objectMapper.convertValue(customersDto, Customers.class);
	}
	
	/**
	 * Metodo convertidor de entidad a dto
	 */
	private static CustomersDto convertEntityToDTO(Customers customers) {
		return objectMapper.convertValue(customers, CustomersDto.class);
	}

}
