package com.nha.java.learning.phoneshop.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.projection.ProductSold;
import com.nha.java.learning.phoneshop.repository.SaleRespositor;
import com.nha.java.learning.phoneshop.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImp implements ReportService{

	private final SaleRespositor saleRespositor;
	
	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRespositor.findProductSold(startDate, endDate);
	}

}
