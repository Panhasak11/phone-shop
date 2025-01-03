package com.nha.java.learning.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nha.java.learning.phoneshop.entity.SaleDetail;

public interface SaleDetailRespositor extends JpaRepository<SaleDetail, Long>{
	List<SaleDetail> findBySaleId(Long saleId);
}
