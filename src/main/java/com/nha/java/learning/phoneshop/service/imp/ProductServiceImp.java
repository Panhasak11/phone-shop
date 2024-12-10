package com.nha.java.learning.phoneshop.service.imp;

import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.exception.ResourceNotFoundException;
import com.nha.java.learning.phoneshop.repository.ProductRepository;
import com.nha.java.learning.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

	private final ProductRepository productRepository;
	@Override
	public Product create(Product product) {
		String name = "%s %s"
				.formatted(product.getModel().getName(),product.getColor().getColor_name());
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", id));
	}

}
