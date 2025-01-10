package com.nha.java.learning.phoneshop.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.nha.java.learning.phoneshop.entity.ProductImportHistory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor	 
public class ProductImportHistorySpec implements Specification<ProductImportHistory>{

	private ProductImportHistoryFilter historyFilter; 
	
	@Override
	@Nullable
	public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query,
			CriteriaBuilder cb) {

		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(historyFilter.getStartDate())) {
			cb.greaterThanOrEqualTo(importHistory.get("dateImport"), historyFilter.getStartDate());
		}
		if(Objects.nonNull(historyFilter.getEndDate())) {
			cb.greaterThanOrEqualTo(importHistory.get("dateImport"), historyFilter.getEndDate());
		}
		//convert from predicates to predicate
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
		return predicate;
	}

}
