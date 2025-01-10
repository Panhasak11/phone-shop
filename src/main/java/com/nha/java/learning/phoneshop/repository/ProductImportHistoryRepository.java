package com.nha.java.learning.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nha.java.learning.phoneshop.entity.ProductImportHistory;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long>, 
				JpaSpecificationExecutor<ProductImportHistory>{

}
