package com.nha.java.learning.phoneshop.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.entity.Brand;
import com.nha.java.learning.phoneshop.exception.ResourceNotFoundException;
import com.nha.java.learning.phoneshop.repository.BrandRepository;
import com.nha.java.learning.phoneshop.service.BrandService;
import com.nha.java.learning.phoneshop.service.util.PageUtil;
import com.nha.java.learning.phoneshop.specification.BrandFilter;
import com.nha.java.learning.phoneshop.specification.BrandSpec;

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

	
	@Override
	public List<Brand> getBrands(String name) {
//		return brandRepository.findByNameLike("%"+name+"%");
		return brandRepository.findByNameContaining(name);

	}

//	@Override
//	public List<Brand> getBrands(Map<String, String> params) {
//		BrandFilter brandFilter = new BrandFilter();
//		
//		if(params.containsKey("name")) {
//			String name = params.get("name");
//			brandFilter.setName(name);
//		}
//		
//		if(params.containsKey("id")) {
//			String id = params.get("id");
//			brandFilter.setId(Integer.parseInt(id));
//		}
//		
//		BrandSpec brandSpec = new BrandSpec(brandFilter);
//		
//		return brandRepository.findAll(brandSpec);
//		
//	}
	
	@Override
	public Page<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
		}
		
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id));
		}
		
		int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		
		BrandSpec brandSpec = new BrandSpec(brandFilter);
		
		Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
		
		Page<Brand> page = brandRepository.findAll(brandSpec, pageable);
		return page;
		
	}
	
}
