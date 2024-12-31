package com.nha.java.learning.phoneshop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbProductImportHistory")
public class ProductImportHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "importId")
	private Long id;
	
	@Column(name = "dateImport")
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateImport;
	
	@Column(name = "importUnit")
	private Integer importUnit;
	
	@Column(name = "pricePerUnit")
	private BigDecimal importPrice;
	
	@ManyToOne
	@JoinColumn(name = "productId") 
	private Product product;
	
}
