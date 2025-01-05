package com.nha.java.learning.phoneshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductReportDTO {

	Long productId;
	String productName;
	Integer unit;
	BigDecimal totalAmount;
}
