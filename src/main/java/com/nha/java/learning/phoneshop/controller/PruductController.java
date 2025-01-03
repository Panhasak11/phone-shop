package com.nha.java.learning.phoneshop.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nha.java.learning.phoneshop.dto.PriceDTO;
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
	
	@PostMapping("{productId}/setSalePrice")
	private ResponseEntity<?> setSalePrice(@PathVariable Long productId, @RequestBody PriceDTO priceDTO){
		productService.setSalePrice(productId, priceDTO.getPrice());
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("uploadProduct")
	private ResponseEntity<?> uploadProduct(@RequestParam("file") MultipartFile inputFile){
		Map<Integer, String> errorMap = productService.uploadProduct(inputFile);
		return ResponseEntity.ok(errorMap);
	}
}
