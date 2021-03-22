package com.compassouol.productms.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="name", nullable=false, length=80)
	private String name;
	
	@Column(name="description", nullable=false, length=150)
	private String description;
	
	@Column(name="price", nullable=false, precision=10, scale=2)
	private BigDecimal price;
	
}
