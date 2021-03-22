package com.compassouol.productms.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.compassouol.productms.api.v1.controller.ProductController;
import com.compassouol.productms.api.v1.link.ProductLink;
import com.compassouol.productms.api.v1.model.ProductModel;
import com.compassouol.productms.domain.model.Product;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductLink productLink;
	
	public ProductModelAssembler() {
		super(ProductController.class, ProductModel.class);
	}
	
	@Override
	public ProductModel toModel(Product product) {
		ProductModel productModel = createModelWithId(product.getId(), product);
		modelMapper.map(product, productModel);
     	productModel.add(productLink.linkToProducts("products"));
		
		return productModel;
	}
	
	@Override
	public CollectionModel<ProductModel> toCollectionModel(Iterable<? extends Product> entities) {
		CollectionModel<ProductModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(productLink.linkToProducts());
		
		return collectionModel;
	}
}
