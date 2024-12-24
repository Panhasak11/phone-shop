package com.nha.java.learning.phoneshop.service.imp;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.dto.ProductImportDTO;
import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.entity.ProductImportHistory;
import com.nha.java.learning.phoneshop.exception.ResourceNotFoundException;
import com.nha.java.learning.phoneshop.mapper.ProductMapper;
import com.nha.java.learning.phoneshop.repository.ProductImportHistoryRepository;
import com.nha.java.learning.phoneshop.repository.ProductRepository;
import com.nha.java.learning.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository importHistoryRepository;
	private final ProductMapper productMapper;
	
	
	@Override
	public Product create(Product product) {
		String name = "%s %s"
				.formatted(product.getModel().getName(),product.getColor().getName());
		product.setProductName(name);
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", id));
	}

	@Override
	public void importProduct(ProductImportDTO importDTO) {
		
		//update available product unit 
		Product product = getById(importDTO.getProductId());
		Integer availableUnit = 0; 
		if(product.getAvilableUnit() != null) {
			availableUnit = product.getAvilableUnit();
		}
		product.setAvilableUnit(availableUnit + importDTO.getImportUnit());
		productRepository.save(product);
		
		//save product import history 
		ProductImportHistory importHistory = productMapper.toProductImport(importDTO, product);
		importHistoryRepository.save(importHistory);
	}

	@Override
	public void setSalePrice(Long productId, BigDecimal price) {
		Product product = getById(productId);
		product.setSalePrice(price);
		productRepository.save(product);
	}


}
