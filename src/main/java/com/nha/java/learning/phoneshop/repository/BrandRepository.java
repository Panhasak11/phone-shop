package com.nha.java.learning.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nha.java.learning.phoneshop.entity.Brand;

@Repository
<<<<<<< HEAD
public interface BrandRepository extends JpaRepository<Brand, Long>{

=======
public interface BrandRepository extends JpaRepository<Brand, Long>, 
				JpaSpecificationExecutor<Brand>{
	
	List<Brand> findByNameIgnoreCase(String name);
	List<Brand> findByNameLike(String name);
	List<Brand> findByNameContaining(String name);
	
>>>>>>> main
}
