package com.nha.java.learning.phoneshop.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbSaleDetail")
public class SaleDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "saleDetailId")
	private Long saleDetailId;
	
	@ManyToOne
	@JoinColumn(name = "saleId")
	private Sale sale;
	

	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "unit")
	private Integer unit;
}
