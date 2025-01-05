package com.nha.java.learning.phoneshop.service;

import java.time.LocalDate;
import java.util.List;

import com.nha.java.learning.phoneshop.dto.ProductReportDTO;
import com.nha.java.learning.phoneshop.projection.ProductSold;

public interface ReportService {

	List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
	List<ProductReportDTO> getProductResport(LocalDate startDate, LocalDate endDate);
}

