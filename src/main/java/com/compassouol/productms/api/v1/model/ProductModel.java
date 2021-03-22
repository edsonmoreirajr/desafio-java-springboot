package com.compassouol.productms.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "products")
@Setter
@Getter
public class ProductModel extends RepresentationModel<ProductModel>{
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private BigDecimal price;
}
