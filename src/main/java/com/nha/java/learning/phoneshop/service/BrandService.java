package com.nha.java.learning.phoneshop.service;

import java.util.List;

import com.nha.java.learning.phoneshop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id, Brand brandUpdate);
	List<Brand> getBrands();
	List<Brand> getBrands(String name);
}