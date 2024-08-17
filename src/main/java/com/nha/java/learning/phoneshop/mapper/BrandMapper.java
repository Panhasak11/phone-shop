package com.nha.java.learning.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.nha.java.learning.phoneshop.dto.BrandDTO;
import com.nha.java.learning.phoneshop.entity.Brand;

@Mapper
public interface BrandMapper {
	
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	
	Brand toBrand(BrandDTO dto);
	
	BrandDTO toBrandDto(Brand entity);
}