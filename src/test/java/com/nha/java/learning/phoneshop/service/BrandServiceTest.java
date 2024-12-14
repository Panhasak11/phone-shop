package com.nha.java.learning.phoneshop.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nha.java.learning.phoneshop.entity.Brand;
import com.nha.java.learning.phoneshop.exception.ResourceNotFoundException;
import com.nha.java.learning.phoneshop.repository.BrandRepository;
import com.nha.java.learning.phoneshop.service.imp.BrandServiceImp;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	
	@Mock
	private BrandRepository brandRepository;
	
	private BrandService brandService;
	
	@BeforeEach
	public void setUp() {
		brandService = new BrandServiceImp(brandRepository);
	}
	
//	@Test
//	public void testCreate() {
////		given
//		Brand brand = new Brand();
//		brand.setName("Apple");
//		brand.setId(1);
//		
////		when
//		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
//		Brand brandReturn = brandService.create(new Brand());
//		
////		then
//		assertEquals(1, brandReturn.getId());
//		assertEquals("Apple", brandReturn.getName());
//	}
	
	@Test
	public void teseCreate() {
		//given
		Brand brand = new Brand();
		brand.setName("Apple");
		
		//when
		brandService.create(brand);
		
		//then
		verify(brandRepository, times(1)).save(brand);
	}
	
	@Test
	public void testGetById() {
//		given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1L);
//		when
		when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getById(1L);
		
//		then
		assertEquals(1, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());
	}
	
	@Test
	public void testGetByIdThrow() {
		//give
		
		//when
		when(brandRepository.findById(2L)).thenReturn(Optional.empty());
		assertThatThrownBy(() -> brandService.getById(2L))
			.isInstanceOf(ResourceNotFoundException.class)
			.hasMessage("Brand with id = 2 not found");
		
//		then
	}
}
