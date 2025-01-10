package com.nha.java.learning.phoneshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nha.java.learning.phoneshop.dto.report.ExpenseReportDTO;
import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.entity.ProductImportHistory;
import com.nha.java.learning.phoneshop.repository.ProductImportHistoryRepository;
import com.nha.java.learning.phoneshop.repository.ProductRepository;
import com.nha.java.learning.phoneshop.repository.SaleDetailRespository;
import com.nha.java.learning.phoneshop.repository.SaleRespository;
import com.nha.java.learning.phoneshop.service.imp.ReportServiceImp;
import com.nha.java.learning.phoneshop.specification.ProductImportHistorySpec;
import com.nha.java.learning.phoneshop.utils.ReportTestHelper;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

	@Mock
	private SaleRespository saleRespository;
	@Mock
	private SaleDetailRespository detailRespository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private ProductImportHistoryRepository productImportHistoryRepository;
	
	private ReportService reportService;
	
	@BeforeEach
	void setUp() {
		reportService = new ReportServiceImp(saleRespository, detailRespository, 
											productRepository, productImportHistoryRepository);
	}
	
	@Test
	void testGetExpenseReport() {
		//given 
		List<ProductImportHistory> histories = ReportTestHelper.getProductImportHistories();
		List<Product> products = ReportTestHelper.getProduct();
		
		//when 
		when(productImportHistoryRepository.findAll(Mockito.any(ProductImportHistorySpec.class)))
				.thenReturn(histories);
		
		when(productRepository.findAllById(anySet()))
				.thenReturn(products);
		
		List<ExpenseReportDTO> expenseReport = reportService.getExpenseReport(LocalDate.now().minusMonths(1),LocalDate.now());
		
		//then
			
		assertEquals(2, expenseReport.size());
		ExpenseReportDTO expenseReportDTO = expenseReport.get(0);
		assertEquals(1, expenseReportDTO.getProductId());
		assertEquals("Iphone 13 pro", expenseReportDTO.getProductName());
		assertEquals(15, expenseReportDTO.getUnit());
		assertEquals(2000d, expenseReportDTO.getTotalAmoutn().doubleValue());
	}
}
