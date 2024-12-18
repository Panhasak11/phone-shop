package com.nha.java.learning.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.entity.ProductImportHistory;

public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long>{

}
