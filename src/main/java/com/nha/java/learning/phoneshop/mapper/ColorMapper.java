package com.nha.java.learning.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.nha.java.learning.phoneshop.dto.ColorDTO;
import com.nha.java.learning.phoneshop.entity.Color;

@Mapper
public interface ColorMapper {

	ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);
	
	Color toColor(ColorDTO colorDTO);
	
	ColorDTO toColorDTO(Color color);
}
