package com.nha.java.learning.phoneshop.service;

import com.nha.java.learning.phoneshop.entity.Brand;

public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id, Brand brandUpdate);
}