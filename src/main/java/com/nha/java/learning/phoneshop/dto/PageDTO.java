package com.nha.java.learning.phoneshop.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageDTO {
		
	private List<?> list;
	private PaginationDTO pagination;
	
	public PageDTO(Page<?> page) {
		this.list = page.getContent();
		this.pagination = PaginationDTO.builder()
				.emty(page.isEmpty())
				.first(page.isFirst())
				.last(page.isLast())
				.pageSize(page.getPageable().getPageSize())
				.pageNumber(page.getPageable().getPageNumber() + 1)
				.totalPage(page.getTotalPages())
				.totalElement(page.getTotalElements())
				.numberOfElements(page.getNumberOfElements())
				.build();
	}
}
