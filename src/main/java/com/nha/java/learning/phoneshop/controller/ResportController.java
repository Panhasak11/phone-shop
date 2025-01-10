package com.nha.java.learning.phoneshop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nha.java.learning.phoneshop.dto.ProductReportDTO;
import com.nha.java.learning.phoneshop.dto.report.ExpenseReportDTO;
import com.nha.java.learning.phoneshop.projection.ProductSold;
import com.nha.java.learning.phoneshop.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("reports")
public class ResportController {

	private final ReportService reportService;
	
	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> getReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate){
		List<ProductSold> productSolds = reportService.getProductSold(startDate, endDate);
		return ResponseEntity.ok(productSolds);
	}
	
	@GetMapping("v2/{startDate}/{endDate}")
	public ResponseEntity<?> getReportv2(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate){
		List<ProductReportDTO> resport = reportService.getProductResport(startDate, endDate);
		return ResponseEntity.ok(resport);
	}
	
	@GetMapping("expense/{startDate}/{endDate}")
	public ResponseEntity<?> getExpenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate){
		List<ExpenseReportDTO> expenseReport = reportService.getExpenseReport(startDate, endDate);
		return ResponseEntity.ok(expenseReport);
		
	}
}
