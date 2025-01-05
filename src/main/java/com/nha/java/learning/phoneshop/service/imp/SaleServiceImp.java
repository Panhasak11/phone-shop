package com.nha.java.learning.phoneshop.service.imp;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nha.java.learning.phoneshop.dto.ProductSoleDTO;
import com.nha.java.learning.phoneshop.dto.SaleDTO;
import com.nha.java.learning.phoneshop.entity.Product;
import com.nha.java.learning.phoneshop.entity.Sale;
import com.nha.java.learning.phoneshop.entity.SaleDetail;
import com.nha.java.learning.phoneshop.exception.ApiException;
import com.nha.java.learning.phoneshop.exception.ResourceNotFoundException;
import com.nha.java.learning.phoneshop.repository.ProductRepository;
import com.nha.java.learning.phoneshop.repository.SaleDetailRespository;
import com.nha.java.learning.phoneshop.repository.SaleRespository;
import com.nha.java.learning.phoneshop.service.ProductService;
import com.nha.java.learning.phoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImp implements SaleService {

	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRespository saleRespository;
	private final SaleDetailRespository saleDetailRespository;
	
	@Override
	public void sell(SaleDTO saleDTO) {
		
		List<Long> productId = saleDTO.getProducts().stream()
			.map(ProductSoleDTO::getProductId)
			.toList();
		
		//validate product
		productId.forEach(productService::getById);
		
		List<Product> products = productRepository.findAllById(productId);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getProductId, Function.identity()));
		
		
		//validate stock
		saleDTO.getProducts()
			.forEach(ps->{
				Product product = productMap.get(ps.getProductId());
				if(product.getAvilableUnit() < ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enoght in stock".formatted(product.getProductName()));
				}
			});
		
		//save sale
		Sale sale = new Sale();
		sale.setSaleDate(saleDTO.getSaleDate());
		saleRespository.save(sale);
		
		//save sale detail
		saleDTO.getProducts().forEach(ps ->{
			Product product = productMap.get(ps.getProductId());
			SaleDetail saleDetail = new SaleDetail();
			saleDetail.setAmount(product.getSalePrice());
			saleDetail.setProduct(product);
			saleDetail.setSale(sale);
			saleDetail.setUnit(ps.getNumberOfUnit());
			saleDetailRespository.save(saleDetail);
			
			//cut stock
			Integer availableUnit = product.getAvilableUnit() - ps.getNumberOfUnit();
			product.setAvilableUnit(availableUnit);
			productRepository.save(product);
			
		});
		
	}
	
	private void validate() {
		//validate product
		
		//validate stock
	}

	@Override
	public void cancelSale(Long saleId) {
		//update sale status
		Sale sale = getBySaleId(saleId);
		sale.setActive(false);
		saleRespository.save(sale);
		
		//update stock
		List<SaleDetail> saleDetails = saleDetailRespository.findBySaleId(saleId);
		
		List<Long> productId = saleDetails.stream()
				.map(sd -> sd.getProduct().getProductId())
				.toList();
		
		List<Product> products = productRepository.findAllById(productId);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getProductId, Function.identity()));
		
		saleDetails.forEach(sd->{
			Product product = productMap.get(sd.getProduct().getProductId());
			product.setAvilableUnit(product.getAvilableUnit() + sd.getUnit());
			productRepository.save(product);
		});
		
	}

	@Override
	public Sale getBySaleId(Long saleId) {
		return saleRespository.findById(saleId)
				.orElseThrow(()-> new ResourceNotFoundException("Sale", saleId));
	}

}
