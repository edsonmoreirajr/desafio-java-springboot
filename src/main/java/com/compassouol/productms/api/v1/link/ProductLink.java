package com.compassouol.productms.api.v1.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.compassouol.productms.api.v1.controller.ProductController;

@Component
public class ProductLink {

	public Link linkToProduct(Long id, String rel) {
		return linkTo(methodOn(ProductController.class)
				.findById(id)).withRel(rel);
	}
	
	public Link linkToProduct(Long id) {
		return linkToProduct(id, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToProducts(String rel) {
		return linkTo(ProductController.class).withRel(rel);
	}
	
	public Link linkToProducts() {
		return linkToProducts(IanaLinkRelations.SELF.value());
	}
}
