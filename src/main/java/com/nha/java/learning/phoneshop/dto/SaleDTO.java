package com.nha.java.learning.phoneshop.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SaleDTO {

	@NotEmpty
	private List<ProductSoleDTO> products;
	private LocalDate saleDate;
}
