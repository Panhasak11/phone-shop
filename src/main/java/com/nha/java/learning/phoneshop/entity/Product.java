package com.nha.java.learning.phoneshop.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "tbProducts", uniqueConstraints = {@UniqueConstraint(columnNames = {"colorId","modelId"})})
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private Long productId;
	
	@Column(name = "productName", unique = true)
	private String productName;
	
	@Column(name = "availableUnit")
	private Integer avilableUnit;
	
	@Column(name = "imagePath")
	private String imagePath;
	
	@ManyToOne
	@JoinColumn(name = "colorId")
	private Color color;
	
	@ManyToOne
	@JoinColumn(name = "modelId")
	private Model model;
	
	@Column(name = "salePrice")
	private BigDecimal salePrice;
	
}
