package com.nha.java.learning.phoneshop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductImportDTO {

	private Long productId;
	private Integer importUnit;
	private BigDecimal importPrice;
	private LocalDateTime importDate;
	
	
}
