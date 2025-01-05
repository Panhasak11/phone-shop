package com.nha.java.learning.phoneshop.specification;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SaleDetailFilter {

	LocalDate startDate;
	LocalDate endDate;
}
