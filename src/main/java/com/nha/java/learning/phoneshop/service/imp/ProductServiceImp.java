package com.nha.java.learning.phoneshop.service.imp;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nha.java.learning.phoneshop.dto.ProductImportDTO;
import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.entity.ProductImportHistory;
import com.nha.java.learning.phoneshop.exception.ApiException;
import com.nha.java.learning.phoneshop.exception.ResourceNotFoundException;
import com.nha.java.learning.phoneshop.mapper.ProductMapper;
import com.nha.java.learning.phoneshop.repository.ProductImportHistoryRepository;
import com.nha.java.learning.phoneshop.repository.ProductRepository;
import com.nha.java.learning.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{

	private final ProductRepository productRepository;
	private final ProductImportHistoryRepository importHistoryRepository;
	private final ProductMapper productMapper;
	
	
	@Override
	public Product create(Product product) {
		String name = "%s %s"
				.formatted(product.getModel().getName(),product.getColor().getName());
		product.setProductName(name);
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", id));
	}

	@Override
	public void importProduct(ProductImportDTO importDTO) {
		
		//update available product unit 
		Product product = getById(importDTO.getProductId());
		Integer availableUnit = 0; 
		if(product.getAvilableUnit() != null) {
			availableUnit = product.getAvilableUnit();
		}
		product.setAvilableUnit(availableUnit + importDTO.getImportUnit());
		productRepository.save(product);
		
		//save product import history 
		ProductImportHistory importHistory = productMapper.toProductImport(importDTO, product);
		importHistoryRepository.save(importHistory);
	}

	@Override
	public void setSalePrice(Long productId, BigDecimal price) {
		Product product = getById(productId);
		product.setSalePrice(price);
		productRepository.save(product);
	}

	@Override
	public Map<Integer, String> uploadProduct(MultipartFile file) {
		Map<Integer, String> map = new HashMap<>();
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheet("products");
			Iterator<Row> rowIterator = sheet.iterator();
			
			//for skip row header
			rowIterator.next(); //@TODO improve checking error
			
			while(rowIterator.hasNext()) {
				Integer rowNo = 0;
				try {
					
					Row row = rowIterator.next();
					int cellIndex = 0;
					
					Cell cellNo = row.getCell(cellIndex++);
					rowNo = (int) cellNo.getNumericCellValue();
					
					Cell cellModelId = row.getCell(cellIndex++);
					Long modelId = (long) cellModelId.getNumericCellValue();
					
					Cell cellColorId = row.getCell(cellIndex++);
					Long colorId = (long) cellColorId.getNumericCellValue();
					
					Cell cellImportPrice = row.getCell(cellIndex++);
					Double importPrice = cellImportPrice.getNumericCellValue();
					
					Cell cellImportUnit = row.getCell(cellIndex++);
					Integer importUnit = (int) cellImportUnit.getNumericCellValue();
					if(importUnit < 1) {
						throw new ApiException(HttpStatus.BAD_REQUEST, "Unit must be greater that 0");
					}
					
					
					Cell cellImportDate = row.getCell(cellIndex++);
					LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();
					System.out.println("Date  " + importDate);
					
					Product product = getByModelIdAndColorId(modelId, colorId);
					
					Integer availableUnit = 0; 
					if(product.getAvilableUnit() != null) {
						availableUnit = product.getAvilableUnit();
					}
					product.setAvilableUnit(availableUnit + importUnit);
					productRepository.save(product);
					
					//save product import history 
					
					 ProductImportHistory importHistory = new ProductImportHistory();
					 importHistory.setDateImport(importDate);
					 importHistory.setImportPrice(BigDecimal.valueOf(importPrice));
					 importHistory.setImportUnit(importUnit); importHistory.setProduct(product);
					 importHistoryRepository.save(importHistory);
					 
				}catch(Exception e) {
					map.put(rowNo, e.getMessage());
				}	
			}	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Product getByModelIdAndColorId(Long modelId, Long colorId) {
		String message = "Product with model id =%s and color id =%d was not found";
		return productRepository.findByModelIdAndColorId(modelId, colorId)
				.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, message.formatted(modelId, colorId)));
	}


}
