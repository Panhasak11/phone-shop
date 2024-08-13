package com.nha.java.learning.phoneshop.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.entity.Brand;
import com.nha.java.learning.phoneshop.exception.ResourceNotFoundException;
import com.nha.java.learning.phoneshop.repository.BrandRepository;
import com.nha.java.learning.phoneshop.service.BrandService;

@Service
public class BranServiceImp implements BrandService{
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Override
	public Brand create(Brand brand) {
//		Brand bran2 = brandRepository.save(brand);
		return brandRepository.save(brand);
	}
	
	@Override
	public Brand getById(Integer id) {
		return brandRepository.findById(id)
//				.orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Brand with id = %d not found".formatted(id)));
				.orElseThrow(()-> new ResourceNotFoundException("Brand", id));
	}

	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());
		return brandRepository.save(brand);
	}
	
}
