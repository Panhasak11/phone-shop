package com.nha.java.learning.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nha.java.learning.phoneshop.dto.SaleDTO;
import com.nha.java.learning.phoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("sales")
public class SaleController {

	private final SaleService saleService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody SaleDTO saleDTO){
		saleService.sell(saleDTO);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{saleId}/cancel")
	private ResponseEntity<?> cancelSale(@PathVariable Long saleId) {
		saleService.cancelSale(saleId);
		return ResponseEntity.ok().build();
	}
}
