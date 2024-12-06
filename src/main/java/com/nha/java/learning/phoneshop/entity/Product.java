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

import lombok.Data;

@Data
@Entity
@Table(name = "tbProducts")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "proId")
	private Long proId;
	
	@Column(name = "proName")
	private String proName;
	
	@Column(name = "availableUnit")
	private Integer avilableUnit;
	
	@Column(name = "imagePath")
	private String imagePath;
	
	@ManyToOne
	@JoinColumn(name = "colorId")
	private Color color;
	
	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;
	
	@Column(name = "salePrice")
	private BigDecimal salePrice;
	
}
