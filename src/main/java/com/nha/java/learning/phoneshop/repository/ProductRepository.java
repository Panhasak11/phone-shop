package com.nha.java.learning.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nha.java.learning.phoneshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
