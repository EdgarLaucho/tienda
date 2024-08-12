package com.tienda.api.service.implement;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda.api.dto.ProductDto;
import com.tienda.api.entity.Product;
import com.tienda.api.exception.ExistsException;
import com.tienda.api.exception.NotFoundException;
import com.tienda.api.repository.ProductRepository;
import com.tienda.api.service.ProductService;

@Service
public class ProductServiceImple implements ProductService {
	
	private Logger logger = LoggerFactory.getLogger(ProductServiceImple.class);
	
	/**
	 * Instancia de objectMapper convertidor de dto a entity y viseversa
	 */
	private static final ObjectMapper objectMapper = new ObjectMapper();

	
	private ProductRepository productRepository;
	
	@Override
	public List<ProductDto> findAll() throws NotFoundException {
		if (logger.isDebugEnabled()) {
			logger.debug("Entrando en el metodo findAll de la clase ProdictServiceImple");
		}
		try {	
			List<Product> listProduct=  productRepository.findAll(); 
			if (listProduct.isEmpty()) {
				
				if (logger.isDebugEnabled()) {
					logger.debug("Lista de productos vacia");
				}
				throw new NotFoundException("No hay registro de productos");
			}
			
			return convertListEntityToListDTO(listProduct);
		} catch (NotFoundException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("No hay registro en la lista");
			}
			throw e;
		}catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Ha ocurrido un error inesperado");
			}
			throw e;
		}
		
	}

	@Override
	public ProductDto save(ProductDto productDto) throws BadRequestException, ExistsException{
		if (logger.isDebugEnabled()) {
			logger.debug("Entrando al metodo save de ProductServiceImple");
		}
		try {
			
			if (productDto == null) {
				productValidation();
			}
			if (productExist(productDto.getProductName())) {
	            if (logger.isErrorEnabled()) {
	                logger.error(String.format("Ha ocurrido un error al guardar el producto, producto ya existe %s",
	                		productDto.toString()));
	            }
	            throw new ExistsException("Producto existente");
	        }
			Product product = convertDTOToEntity(productDto);
			
			Product productSave= productRepository.save(product);
			return convertEntityToDTO(productSave);
			
		} catch (BadRequestException | ExistsException e) {
			throw e;
		}catch (Exception i) {
			if (logger.isErrorEnabled()) {
				logger.error("Error desconocido en save");
			}
			throw i;
		}
		
	}

	@Override
	public ProductDto findById(Long id) throws NotFoundException, BadRequestException {
		if (logger.isDebugEnabled()) {
			logger.debug("Entrando al metodo findById de la clase ProductServiceImple");
		}
		try {
			if (id == null) {
				fieldValidationId();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public ProductDto update(ProductDto productDto) throws NotFoundException, BadRequestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(Long id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<InputStreamResource> customersGenerateReport() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void productValidation() throws BadRequestException {
		if (logger.isDebugEnabled()) {
			logger.debug("No ha ingresado ningun dato");
		}
		throw new BadRequestException("Debe LLenar todos los campos");
	}
	
	/**
	 * Metodo convertidor de dto a entidad
	 */
	private static Product convertDTOToEntity(ProductDto productDto) {
		return objectMapper.convertValue(productDto, Product.class);
	}
	
	/**
	 * Metodo convertidor de entidad a dto
	 */
	private static ProductDto convertEntityToDTO(Product product) {
		return objectMapper.convertValue(product, ProductDto.class);
	}
	
	/**
	 * Metodo convertidos lista entidad a lista dto
	 * 
	 * @param employees :Entidad del sistema
	 * @return Lista de empleados
	 */
	private List<ProductDto> convertListEntityToListDTO(List<Product> product) {
		return product.stream().map(Product -> objectMapper.convertValue(product, ProductDto.class)).toList();
	}

	private boolean productExist(String productName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Entrando en el metodo customerExist de la clase CustomersServiceImple");
		}
		return productRepository.existsByProductName(productName);
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
