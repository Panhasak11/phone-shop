package com.nha.java.learning.phoneshop.service.imp;

import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.entity.Color;
import com.nha.java.learning.phoneshop.exception.ResourceNotFoundException;
import com.nha.java.learning.phoneshop.repository.ColorRepository;
import com.nha.java.learning.phoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImp implements ColorService{

	private final ColorRepository colorRepository;
	@Override
	public Color creat(Color color) {
		// TODO Auto-generated method stub
		return colorRepository.save(color);
	}

	@Override
	public Color getById(Long colorId) {
		// TODO Auto-generated method stub 
		return colorRepository.findById(colorId)
				.orElseThrow(()-> new ResourceNotFoundException("Color", colorId));
	}

}
