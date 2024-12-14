package com.nha.java.learning.phoneshop.service;

import java.util.List;

import com.nha.java.learning.phoneshop.entity.Model;

public interface ModelService{
	
	Model save(Model model);
	List<Model> findByBrandId(Long id);
	Model getById(Long id);
	Model update(Long id,Model modelUpdate);

}
