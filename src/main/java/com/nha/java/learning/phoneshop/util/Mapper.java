package com.nha.java.learning.phoneshop.util;

import com.nha.java.learning.phoneshop.dto.BrandDTO;
import com.nha.java.learning.phoneshop.entity.Brand;

public class Mapper {

	public static Brand toBrand(BrandDTO dto) {
		Brand brand = new Brand();
//		brand.setId(dto.getId());
		brand.setName(dto.getName());
		return brand;
	}
	
	public static BrandDTO toBrandDto(Brand brand) {
		BrandDTO brandDTO = new BrandDTO();
//		brandDTO.setId(brand.getId());
		brandDTO.setName(brand.getName());
		return brandDTO;
	}
}
