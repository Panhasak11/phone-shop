package com.nha.java.learning.phoneshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nha.java.learning.phoneshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findByModelIdAndColorId(Long model, Long colorId);
}
