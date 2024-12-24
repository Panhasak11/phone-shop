package com.nha.java.learning.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nha.java.learning.phoneshop.entity.Sale;

public interface SaleRespositor extends JpaRepository<Sale, Long>{

}
