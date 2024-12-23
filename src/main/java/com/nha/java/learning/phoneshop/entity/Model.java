package com.nha.java.learning.phoneshop.entity;


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
@Table(name = "tbModels")
public class Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "modelId")
	private Long id;
	@Column(name = "modelName")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "brandId")
	private Brand brand;
}
