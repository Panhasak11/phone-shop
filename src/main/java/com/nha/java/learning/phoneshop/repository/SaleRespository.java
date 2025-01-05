package com.nha.java.learning.phoneshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nha.java.learning.phoneshop.entity.Sale;
import com.nha.java.learning.phoneshop.projection.ProductSold;

public interface SaleRespository extends JpaRepository<Sale, Long>{

	@Query(value = "SELECT p.product_id AS productId, p.product_name AS productName, \r\n"
			+ "	SUM(sd.unit) AS totalUnit, SUM(sd.unit * sd.amount) AS totalAmount \r\n"
			+ "	FROM tb_sale_detail sd\r\n"
			+ "	INNER JOIN tb_sale s ON sd.sale_id = s.sale_id\r\n"
			+ "	INNER JOIN tb_products p ON p.product_id = sd.product_id\r\n"
			+ "WHERE date(s.sale_date) >= :startDate and date(s.sale_date) <= :endDate\r\n"
			+ "GROUP BY p.product_id, p.product_name", nativeQuery = true)
	List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
}
