package com.nha.java.learning.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nha.java.learning.phoneshop.dto.ModelDTO;
import com.nha.java.learning.phoneshop.entity.Model;
import com.nha.java.learning.phoneshop.mapper.ModelMapper;
import com.nha.java.learning.phoneshop.service.ModelService;
import com.nha.java.learning.phoneshop.service.imp.BranServiceImp;

import lombok.RequiredArgsConstructor;

//class controller is used for map url to come any handler

@RequiredArgsConstructor 
@RestController
@RequestMapping("/models") 
public class ModelController {

	//we can use @Autowire without use final keyword
	//but this style we make constructor that used @RequiredArgsConstructor  
	private final ModelService modelService;
	private final ModelMapper modelMapper;
	
//	@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO modelDTO){
		Model model = modelMapper.toModel(modelDTO);
		model = modelService.save(model);
		return ResponseEntity.ok((modelMapper.toModelDTO(model)));
	}
}
