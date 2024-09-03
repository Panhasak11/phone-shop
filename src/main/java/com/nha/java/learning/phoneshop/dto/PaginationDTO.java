package com.nha.java.learning.phoneshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {

	private int pageSize;
	private int pageNumber;
	private long totalElement;
	private long totalPage;
	private int numberOfElements;
	
	private boolean first;
	private boolean last;
	private boolean emty;
	
	
}
