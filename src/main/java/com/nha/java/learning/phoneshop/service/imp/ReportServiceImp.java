package com.nha.java.learning.phoneshop.service.imp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.aspectj.weaver.ast.Var;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.dto.ProductReportDTO;
import com.nha.java.learning.phoneshop.dto.ProductSoleDTO;
import com.nha.java.learning.phoneshop.dto.report.ExpenseReportDTO;
import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.entity.ProductImportHistory;
import com.nha.java.learning.phoneshop.entity.SaleDetail;
import com.nha.java.learning.phoneshop.projection.ProductSold;
import com.nha.java.learning.phoneshop.repository.ProductImportHistoryRepository;
import com.nha.java.learning.phoneshop.repository.ProductRepository;
import com.nha.java.learning.phoneshop.repository.SaleDetailRespository;
import com.nha.java.learning.phoneshop.repository.SaleRespository;
import com.nha.java.learning.phoneshop.service.ReportService;
import com.nha.java.learning.phoneshop.specification.ProductImportHistoryFilter;
import com.nha.java.learning.phoneshop.specification.ProductImportHistorySpec;
import com.nha.java.learning.phoneshop.specification.SaleDetailFilter;
import com.nha.java.learning.phoneshop.specification.SaleDetaileSpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImp implements ReportService{

	private final SaleRespository saleRespository;
	private final SaleDetailRespository detailRespository;
	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository historyRepository;
	
	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		return saleRespository.findProductSold(startDate, endDate);
	}

	@Override
	public List<ProductReportDTO> getProductResport(LocalDate startDate, LocalDate endDate) {
		
		List<ProductReportDTO> list = new ArrayList<>();
		
		//filter date
		SaleDetailFilter saleFilter = new SaleDetailFilter();
		saleFilter.setStartDate(startDate);
		saleFilter.setEndDate(endDate);
		
		//get all data from saleDetailSpec
		Specification<SaleDetail> spec = new SaleDetaileSpec(saleFilter);
		List<SaleDetail> saleDetails = detailRespository.findAll(spec);
		
		//get by productId from sale detail
		List<Long> productId = saleDetails.stream()
			.map(sd -> sd.getProduct().getProductId())
			.toList();
		
		//Map product sold 
		Map<Long, Product> productMap = productRepository.findAllById(productId).stream()
			.collect(Collectors.toMap(Product::getProductId, Function.identity()));
		System.out.println("All product = "+productMap);
		
		//group saleDetail by product 
		Map<Product, List<SaleDetail>> saleDetailMap = saleDetails.stream()
			.collect(Collectors.groupingBy(SaleDetail::getProduct));
		
		for(var entry: saleDetailMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getProductId());
			
			// get value from sale detail 
			List<SaleDetail> sdList = entry.getValue();
			
			//total  unit 
			Integer unit = sdList.stream().map(SaleDetail::getUnit)
				.reduce(0, (a,b)-> a+b);
			
			//total amount 
			double totalAmount = sdList.stream()
				.mapToDouble(sd-> sd.getUnit()*sd.getAmount().doubleValue()).sum();
			
			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getProductId());
			reportDTO.setProductName(product.getProductName());
			reportDTO.setUnit(unit);
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			list.add(reportDTO);
		}
		
		return list;
	}

	@Override
	public List<ExpenseReportDTO> getExpenseReport(LocalDate startDate, LocalDate endDate) {
		ProductImportHistoryFilter filter = new ProductImportHistoryFilter();
		filter.setStartDate(startDate);
		filter.setEndDate(endDate);
		
		ProductImportHistorySpec spec = new ProductImportHistorySpec(filter);
		List<ProductImportHistory> importHistories = historyRepository.findAll(spec);
		
		Set<Long> productIds = importHistories.stream()
			.map(his -> his.getProduct().getProductId())
			.collect(Collectors.toSet());
		
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(p-> p.getProductId(), p->p));
		
		//group by product
		Map<Product, List<ProductImportHistory>> importMap = importHistories.stream()
			.collect(Collectors.groupingBy(pi -> pi.getProduct()));
		
		var expenseReportDTOs = new ArrayList<ExpenseReportDTO>();
		
		for(var entry: importMap.entrySet()){
			
			Product product = productMap.get(entry.getKey().getProductId());
			List<ProductImportHistory> importProduct = entry.getValue();
			
			int totalUnit = importProduct.stream()
				.mapToInt(pi->pi.getImportUnit()).sum();
			
			double totalAmount = importProduct.stream()
				.mapToDouble(pi -> pi.getImportUnit()* pi.getImportPrice().doubleValue())
				.sum();
			
			var expenseReportDTO = new ExpenseReportDTO();
			expenseReportDTO.setProductId(product.getProductId());
			expenseReportDTO.setProductName(product.getProductName());
			expenseReportDTO.setUnit(totalUnit);
			expenseReportDTO.setTotalAmoutn(BigDecimal.valueOf(totalAmount));
			expenseReportDTOs.add(expenseReportDTO);
		}	
		Collections.sort(expenseReportDTOs, (a,b)-> (int)(a.getProductId() - b.getProductId()));
		return expenseReportDTOs;
	}
}
