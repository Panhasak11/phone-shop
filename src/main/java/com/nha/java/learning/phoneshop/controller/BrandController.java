package com.nha.java.learning.phoneshop.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nha.java.learning.phoneshop.dto.BrandDTO;
import com.nha.java.learning.phoneshop.entity.Brand;
import com.nha.java.learning.phoneshop.mapper.BrandMapper;
import com.nha.java.learning.phoneshop.service.BrandService;

@RestController
@RequestMapping("brands")

public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer branId, @RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brand updateBrand = brandService.update(branId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(updateBrand));
	}

	@GetMapping
	public ResponseEntity<?> getBrands(){
		
		List<BrandDTO> list = brandService.getBrands() 
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDto(brand))
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(list);
		
//		return ResponseEntity.ok(brandService.getBrands());
	}
	
	@GetMapping("filter")
	public ResponseEntity<?> getBrands(@RequestParam("name") String name){
		
		List<BrandDTO> list = brandService.getBrands(name) 
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDto(brand))
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(list);
		
//		return ResponseEntity.ok(brandService.getBrands());
	}
}
