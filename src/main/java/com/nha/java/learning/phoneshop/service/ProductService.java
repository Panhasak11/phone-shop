package com.nha.java.learning.phoneshop.service;

import java.math.BigDecimal;

import com.nha.java.learning.phoneshop.dto.ProductImportDTO;
import com.nha.java.learning.phoneshop.entity.Product;

public interface ProductService {


	Product create(Product product);
	Product getById(Long id);
	void importProduct(ProductImportDTO importDTO);
	void setSalePrice(Long productId,BigDecimal price);
}
