package com.nha.java.learning.phoneshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nha.java.learning.phoneshop.entity.SaleDetail;

public interface SaleDetailRespository extends JpaRepository<SaleDetail, Long>, 
				JpaSpecificationExecutor<SaleDetail>{
	
	List<SaleDetail> findBySaleId(Long saleId);
}
