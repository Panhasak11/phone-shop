package com.nha.java.learning.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nha.java.learning.phoneshop.dto.ColorDTO;
import com.nha.java.learning.phoneshop.entity.Color;
import com.nha.java.learning.phoneshop.mapper.ColorMapper;
import com.nha.java.learning.phoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController 
@RequestMapping("colors")
public class ColorController {
	
	private final ColorService colorService;
//	private final ColorMapper colorMapper;
	
	@PostMapping
	public ResponseEntity<?> createColor(@RequestBody ColorDTO colorDTO){
		Color color = ColorMapper.INSTANCE.toColor(colorDTO);
		color = colorService.creat(color);
		return ResponseEntity.ok(ColorMapper.INSTANCE.toColorDTO(color));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getColor(@PathVariable Long id){
		Color color = colorService.getById(id);
		return ResponseEntity.ok(ColorMapper.INSTANCE.toColorDTO(color));
	}
}
