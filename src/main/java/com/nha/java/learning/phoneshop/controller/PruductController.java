package com.nha.java.learning.phoneshop.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nha.java.learning.phoneshop.dto.ProductDTO;
import com.nha.java.learning.phoneshop.dto.ProductImportDTO;
import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.mapper.ProductMapper;
import com.nha.java.learning.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class PruductController {
	
	private final ProductMapper productMapper;
	private final ProductService productService;

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<?> create(@RequestBody ProductDTO dto){
		Product product = productMapper.toProduct(dto);
		product = productService.create(product);
		return ResponseEntity.ok(product);	
	}
	
	@PostMapping("import")
	private ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDTO importDTO){
		productService.importProduct(importDTO);
		
		return ResponseEntity.ok().build();
	}
}
