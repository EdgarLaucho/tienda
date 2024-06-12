package com.tienda.api.service.implement;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.api.dto.EmployeesDto;
import com.tienda.api.entity.Employees;
import com.tienda.api.exception.NotFoundException;
import com.tienda.api.repository.EmployeesRepository;
import com.tienda.api.service.EmployeesService;

public class EmployeesServiceImple  implements EmployeesService{
	
	/**
	 * Instancia de logger para trazas.
	 */
	private static final Logger logger = LoggerFactory.getLogger(EmployeesServiceImple.class);

	/**
	 * Instancia de objectMapper convertidor de dto a entity y viseversa
	 */
	private static final ObjectMapper objectMapper = new ObjectMapper();
	

	private final EmployeesRepository employeesRepository;
	public EmployeesServiceImple(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

	
	@Override
	public EmployeesDto findById(Long id) throws NotFoundException {
		if (logger.isDebugEnabled()) {
			logger.debug("Entrando ha EmployeesServiceImplement metodo findById");
		}
		try {
			Optional<Employees> employees = employeesRepository.findById(id);
			if(employees.isEmpty()) {
				if (logger.isErrorEnabled()) {
					logger.error(String.format("El identificador no existe %s", id));
				}
				throw new NotFoundException("El identificador del empleado no existe");
			}else {
				if (logger.isDebugEnabled()) {
					logger.debug("Se ha encontrado al usuario");
				}
			}
			return convertEntityToDTO(employees.get());
		} catch (NotFoundException e) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format("Error al buscar empleado %s", id),e);
				}
			throw e;
		}catch (Exception i) {
			
			logger.error("Error a buscar empleado");
			throw i;
		}
		
	}

	 @Override
	    public List<EmployeesDto> findAll() {
	        if (logger.isDebugEnabled()) {
	            logger.debug("Entrando al método findAll de EmployeesServiceImple");
	        }
	        try {
	            List<Employees> employees = employeesRepository.findAll();
	            return convertListEntityToListDTO(employees);
	        } catch (Exception e) {
	        	if (logger.isErrorEnabled()) {
	        		logger.error("Error al recuperar la lista de empleados");
				}
	            
	            throw e;
	        }
	    }

	@Override
	public EmployeesDto save(EmployeesDto employeesDto) {
		if (logger.isDebugEnabled()) {
			logger.debug("Entrando en el metodo save de EmployeesServiceImple");
		}
		try {
			if (employeesDto != null) {
				Employees employees = convertDTOToEntity(employeesDto);
				employees= employeesRepository.save(employees);
				return convertEntityToDTO(employees);
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public EmployeesDto updateById(EmployeesDto employeesDto) {
		if (logger.isDebugEnabled()) {
			logger.debug("Entrando en el metodo updateById de EmployeesServiceImple");
		}
		try {
			
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public EmployeesDto deleteById(Long id) {

		return null;
	}
	
	/**
	 * Metodo convertidor de entidad a dto
	 */
	private static EmployeesDto convertEntityToDTO(Employees employees) {
		if (employees != null && employees.getEmployeeName() != null) {
			employees.setEmployeeName(employees.getEmployeeName().toUpperCase());
		}
		return objectMapper.convertValue(employees, EmployeesDto.class);
	}

	
	/**
	 * Metodo convertidor de dto a entidad
	 */
	private static Employees convertDTOToEntity(EmployeesDto dto) {
		return objectMapper.convertValue(dto, Employees.class);
	}
	/**
	 * Metodo convertidos lista entidad a lista dto
	 * @param employees :Entidad del sistema
	 * @return Lista de empleados
	 */
	 private List<EmployeesDto> convertListEntityToListDTO(List<Employees> employees) {
		 return employees.stream()
	                .map(employee -> objectMapper.convertValue(employee, EmployeesDto.class))
	                .toList();
	    }

}
