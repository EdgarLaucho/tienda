package com.tienda.api.service.implement;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.api.dto.CustomersDto;
import com.tienda.api.entity.Customers;
import com.tienda.api.exception.ExistsException;
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
	public CustomersDto save(CustomersDto customersDto) throws BadRequestException, ExistsException {
	    try {
	        // Validación del DTO al inicio
	    	
	        if (customersDto == null) {
	            fieldValidation();
	        }

	        // Verificación de existencia del cliente
	        if (customerExist(customersDto.getCustomersName(), customersDto.getCustomerLastName())) {
	            if (logger.isErrorEnabled()) {
	                logger.error(String.format("Ha ocurrido un error al guardar el cliente, cliente ya existe %s",
	                        customersDto.toString()));
	            }
	            throw new ExistsException("Cliente existente");
	        }

	        // Registro de depuración
	        if (logger.isDebugEnabled()) {
	            logger.debug("Entrando al metodo save de la clase CustomersServiceImple");
	        }

	        // Convertir DTO a entidad
	        Customers customers = convertDTOToEntity(customersDto);
	        
	        // Guardar la entidad
	        Customers savedCustomer = customersRepository.save(customers);
	        
	        // Convertir la entidad guardada de vuelta a DTO
	        return convertEntityToDTO(savedCustomer);
	    } catch (BadRequestException | ExistsException e) {
	        throw e;
	    } catch (Exception e) {
	        if (logger.isErrorEnabled()) {
	            logger.error("Error desconocido en save");
	        }
	        throw new RuntimeException("Error desconocido" + e); 
	    }
	}



	


	@Override
	public CustomersDto findById(Long id) throws NotFoundException, BadRequestException {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Entrando en el metodo findById de la clase CustomersServiceImple");
			}
			if (id==null) {
				fieldValidationId();
			}
			
			Optional<Customers> customer = customersRepository.findById(id);
			if (customer.isEmpty()) {
				if (logger.isErrorEnabled()) {
					logger.error(String.format("El identificador del cliente no existe %s", id));
				}
				throw new NotFoundException("El identificador del cliente no existe");
			}else {
				if (logger.isDebugEnabled()) {
					logger.debug("Cliente encontrado");
				}
			}
			return convertEntityToDTO(customer.get());
			
		}catch (NotFoundException a) {
			throw a;
		}catch (BadRequestException e) {
			throw e;
		} catch (Exception i) {
			throw i;
		}
		
	}

	@Override
	public CustomersDto update(CustomersDto customersDto) throws NotFoundException,BadRequestException {
		try {
			if (customersDto == null) {
	            fieldValidation();
	        }
			if (customersDto.getCustomerId()==null) {
				fieldValidationId();
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Entrando en el metodo Update de la calse CustomersServiceImple");
			}
			Optional<Customers> customer = customersRepository.findById(customersDto.getCustomerId());
			if (customer.isPresent()) {
				Customers customerUpdate= customer.get();
				this.updateCustomer(customersDto,customerUpdate);
			}else {
				if (logger.isErrorEnabled()) {
					logger.error(String.format("Error al registrar cliente identificador no encontrado : %s", customersDto.getCustomerId()));
				}
				throw new NotFoundException("No se encontro al cliente" + customersDto.getCustomerId());
			}
		} catch (NotFoundException a) {
			throw a;
		}catch (BadRequestException e) {
			throw e;
		}catch (Exception i) {
			if (logger.isErrorEnabled()) {
				logger.error("Error desconocido");
			}
			throw i;
		}
		
		return customersDto;
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
	
	/**Metodo de validacion si existe el usuario
	 * 
	 */
	private boolean customerExist(String customersName, String customerLastName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Entrando en el metodo customerExist de la clase CustomersServiceImple");
		}
		return customersRepository.customerExist(customersName, customerLastName );
	}
	

	/**
	 * Metodo Validacion de que los campos no esten vacios
	 * @throws BadRequestException
	 */
	private void fieldValidation() throws BadRequestException {
		if (logger.isErrorEnabled()) {
			logger.error("No hay datos");
		}
		throw new BadRequestException("Los datos estan vacios");
	}

	/**
	 * Metodo Actualizacion de cliente
	 */
	private void updateCustomer(CustomersDto customersDto, Customers customerUpdate) {
		//si hay cambios se verifica si el cliente ya existe
		if (!customerUpdate.getCustomersName().equals(customersDto.getCustomersName())
				|| !customerUpdate.getCustomerLastName().equals(customersDto.getCustomerLastName())) {
			this.customerExists(customersDto);
			customerUpdate.setCustomersName(customersDto.getCustomerLastName());
			customerUpdate.setCustomerLastName(customersDto.getCustomerLastName());
			customerUpdate.setCustomerBalance(customersDto.getCustomerBalance());
			customersRepository.save(customerUpdate);
			if (logger.isDebugEnabled()) {
				logger.debug("Cliente actualizado");
			}
		}
		
	}
	/**
	 * Metodo validacion de que cliente no se repita
	 */
	private void customerExists(CustomersDto customersDto) {
		boolean customerExists =customerExist(customersDto.getCustomersName(), customersDto.getCustomerLastName());
		if (customerExists) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format("Cliente ya existe nombre: %s , apellido: %s", customersDto.getCustomersName(), customersDto.getCustomerLastName()));
			}
			throw new ExistsException("Cliente ya existe");
		}
		
	}
	/**
	 * Metodo validacion id
	 * @throws BadRequestException El identificador del campo no puede estar vacio
	 */
	private void fieldValidationId() throws BadRequestException {
		if (logger.isErrorEnabled()) {
			logger.error("El identificador esta vacio");
		}
		throw  new BadRequestException("El identificador no puede estar vacio");
	}
}
