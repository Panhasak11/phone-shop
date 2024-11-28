package com.nha.java.learning.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.nha.java.learning.phoneshop.dto.ModelDTO;
import com.nha.java.learning.phoneshop.entity.Model;
import com.nha.java.learning.phoneshop.service.BrandService;

@Mapper(componentModel = "spring",uses = {BrandService.class})
public interface ModelEntityMapper {
	
	ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);
	
	@Mapping(target = "brand", source = "brandId")
	Model toModel(ModelDTO dto);
	
	@Mapping(target = "brandId", source = "brand.id")
	ModelDTO toModelDTO(Model model);
	
	//crate implementation 
	/*
	 * default Brand toBrand(Integer brId) { Brand brand = new Brand();
	 * brand.setId(brId); return brand; }
	 */
	
}
