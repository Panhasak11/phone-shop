package com.nha.java.learning.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nha.java.learning.phoneshop.dto.ProductDTO;
import com.nha.java.learning.phoneshop.dto.ProductImportDTO;
import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.entity.ProductImportHistory;
import com.nha.java.learning.phoneshop.service.ColorService;
import com.nha.java.learning.phoneshop.service.ModelService;

@Mapper(componentModel = "spring", uses = {ModelService.class,ColorService.class})
public interface ProductMapper {

	//method overload can has existing name but different parameter 
	
	@Mapping(target = "model",source = "modelId")
	@Mapping(target = "color",source = "colorId")
	Product toProduct(ProductDTO productDTO);
	
//	@Mapping(target = "dateImport",source = "importDTO.dateImport")
//	@Mapping(target = "pricePerUnit", source ="importDTO.importPrice")
	@Mapping(target = "product",source = "product")
	@Mapping(target = "id", ignore = true)
	ProductImportHistory toProductImport(ProductImportDTO importDTO, Product product);
}
