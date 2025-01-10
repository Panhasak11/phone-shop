package com.nha.java.learning.phoneshop.dto.report;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseReportDTO {

	private String title;
	private Long productId;
	private String productName;
	private Integer unit;
	private BigDecimal totalAmoutn;
	
}
