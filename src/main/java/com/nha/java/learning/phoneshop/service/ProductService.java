package com.nha.java.learning.phoneshop.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nha.java.learning.phoneshop.dto.ProductImportDTO;
import com.nha.java.learning.phoneshop.entity.Product;

public interface ProductService {


	Product create(Product product);
	Product getById(Long id);
	Product getByModelIdAndColorId(Long modelId, Long colorId);
	void importProduct(ProductImportDTO importDTO);
	void setSalePrice(Long productId,BigDecimal price);
	Map<Integer, String> uploadProduct(MultipartFile file);
}
