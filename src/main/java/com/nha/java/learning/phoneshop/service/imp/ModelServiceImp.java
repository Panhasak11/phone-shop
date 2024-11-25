package com.nha.java.learning.phoneshop.service.imp;

import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.entity.Model;
import com.nha.java.learning.phoneshop.repository.ModelRepository;
import com.nha.java.learning.phoneshop.service.BrandService;
import com.nha.java.learning.phoneshop.service.ModelService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class ModelServiceImp implements ModelService{

	//this is the dependency mendatory so put it to parameter's constructor
	//without use @Autowired but used @AllArgsConstructor
	private ModelRepository modelRepository;
	
	@Override
	public Model save(Model model) {
		return modelRepository.save(model);
	
	}

}
