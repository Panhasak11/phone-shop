package com.nha.java.learning.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nha.java.learning.phoneshop.dto.ProductDTO;
import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.service.ColorService;
import com.nha.java.learning.phoneshop.service.ModelService;

@Mapper(componentModel = "spring", uses = {ModelService.class,ColorService.class})
public interface ProductMapper {

	@Mapping(target = "model",source = "modelId")
	@Mapping(target = "color",source = "colorId")
	Product toProduct(ProductDTO productDTO);
}
