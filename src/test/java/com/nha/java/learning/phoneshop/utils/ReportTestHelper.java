package com.nha.java.learning.phoneshop.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.entity.ProductImportHistory;

public class ReportTestHelper {

	private static Product product1 = Product.builder()
			.productId(1L)
			.productName("Iphone 13 pro")
			.build();
	
	private static Product product2 = Product.builder()
			.productId(2L)
			.productName("Iphone 11 pro")
			.build();
	
	private static Product product3 = Product.builder()
			.productId(3L)
			.productName("Iphone 14")
			.build();
	
	public  static List<Product> getProduct(){
		return List.of(product1,product2);
	}
	
	public static List<ProductImportHistory> getProductImportHistories(){
		ProductImportHistory history1 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(10)
				.importPrice(BigDecimal.valueOf(100))
				.dateImport(LocalDateTime.of(2025, 1, 1, 10, 30))
				.build();
		
		ProductImportHistory history2 = ProductImportHistory.builder()
				.product(product2)
				.importUnit(5)
				.importPrice(BigDecimal.valueOf(100))
				.dateImport(LocalDateTime.of(2025, 1, 1, 10, 30))
				.build();
		
		ProductImportHistory history3 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(5)
				.importPrice(BigDecimal.valueOf(200))
				.dateImport(LocalDateTime.of(2025, 1, 5, 11, 30))
				.build();
		
		return List.of(history1,history2,history3);
	}
}
