package com.nha.java.learning.phoneshop.service;

import com.nha.java.learning.phoneshop.dto.SaleDTO;
import com.nha.java.learning.phoneshop.entity.Sale;

public interface SaleService {

	void sell(SaleDTO saleDTO);
	Sale getBySaleId(Long saleId);
	void cancelSale(Long saleId);
}
