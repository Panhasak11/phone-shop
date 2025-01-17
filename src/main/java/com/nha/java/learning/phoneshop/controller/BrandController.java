package com.nha.java.learning.phoneshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nha.java.learning.phoneshop.dto.BrandDTO;
import com.nha.java.learning.phoneshop.dto.ModelDTO;
import com.nha.java.learning.phoneshop.dto.PageDTO;
import com.nha.java.learning.phoneshop.entity.Brand;
import com.nha.java.learning.phoneshop.entity.Model;
import com.nha.java.learning.phoneshop.mapper.BrandMapper;
import com.nha.java.learning.phoneshop.mapper.ModelEntityMapper;
import com.nha.java.learning.phoneshop.service.BrandService;
import com.nha.java.learning.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("brands")
public class BrandController {
	
	private final BrandService brandService;
	private final ModelService modelService;
	private final ModelEntityMapper modelMapper;
	
	@PreAuthorize("hasAuthority('brand:write')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
	}	
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long branId, @RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brand updateBrand = brandService.update(branId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(updateBrand));
	}
	
	@PreAuthorize("hasAuthority('brand:read')")
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		
		Page<Brand> page = brandService.getBrands(params);
		
		PageDTO pageDTO = new PageDTO(page);
		
//		List<BrandDTO> list = brandService.getBrands(params) 
//			.stream()
//			.map(brand -> BrandMapper.INSTANCE.toBrandDto(brand))
//			.collect(Collectors.toList());
		
		return ResponseEntity.ok(pageDTO);
//		return null;
	}
	
	@GetMapping("{id}/models")
	public ResponseEntity<?> getModelsByBrand(@PathVariable("id") Long brandId){
		List<Model> brands = modelService.findByBrandId(brandId);
		List<ModelDTO> list = brands.stream()
			.map(modelMapper::toModelDTO)
			.toList();
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable("id") Long brandId){
		brandService.deleteById(brandId);
		return ResponseEntity.ok().build();
	}
}
