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
@Table(name = "tbProImportHistory")
public class ProductImportHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proImportId")
	private Long proImportId;
	
	@Column(name = "dateImport")
	private LocalDateTime dateImport;
	
	@Column(name = "importUnit")
	private Integer importUnit;
	
	@Column(name = "pricePerUnit")
	private BigDecimal pricePerUnit;
	
	@ManyToOne
	@JoinColumn(name = "proId")
	private Product product;
	
}
