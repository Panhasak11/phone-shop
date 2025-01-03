package com.nha.java.learning.phoneshop.projection;

import java.math.BigDecimal;

public interface ProductSold {
	
	//productId, productName, unit, totalAmount
	Long getProductId();
	String getProductName();
	Integer getTotalUnit();
	BigDecimal getTotalAmount();
}
