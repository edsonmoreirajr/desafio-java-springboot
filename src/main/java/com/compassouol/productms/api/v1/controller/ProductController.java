package com.compassouol.productms.api.v1.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.productms.api.ResourceUriHelper;
import com.compassouol.productms.api.v1.assembler.ProductInputDisassembler;
import com.compassouol.productms.api.v1.assembler.ProductModelAssembler;
import com.compassouol.productms.api.v1.model.ProductModel;
import com.compassouol.productms.api.v1.model.input.ProductInput;
import com.compassouol.productms.core.data.PageWrapper;
import com.compassouol.productms.core.data.PageableTranslator;
import com.compassouol.productms.domain.exception.not_found.ProductNaoEncontradoException;
import com.compassouol.productms.domain.model.Product;
import com.compassouol.productms.domain.repository.ProductRepository;
import com.compassouol.productms.domain.repository.spec.ProductSpecs;
import com.compassouol.productms.domain.service.ProductService;

@RestController
@RequestMapping(path = "/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	private ProductService productService;
	private ProductRepository productRepository;
	private ProductModelAssembler productModelAssembler;
	private ProductInputDisassembler productInputDisassembler;
	private PagedResourcesAssembler<Product> pagedResourcesAssembler;

	public ProductController(ProductService productService, ProductRepository productRepository,
			ProductModelAssembler productModelAssembler, ProductInputDisassembler productInputDisassembler,
			PagedResourcesAssembler<Product> pagedResourcesAssembler) {
		this.productService = productService;
		this.productRepository = productRepository;
		this.productModelAssembler = productModelAssembler;
		this.productInputDisassembler = productInputDisassembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}

	@GetMapping
	public PagedModel<ProductModel> listAll(@PageableDefault(size = 10) Pageable pageable) {

		Page<Product> productsPage = productService.listAll(pageable);

		return pagedResourcesAssembler.toModel(productsPage,
				productModelAssembler);
	}

	@GetMapping("/search")
	public PagedModel<ProductModel> search(@RequestParam(required=false) String q, @RequestParam(required=false) BigDecimal minPrice,
			@RequestParam(required=false) BigDecimal maxPrice, @PageableDefault(size = 10) Pageable pageable) {

		Page<Product> pedidosPage = productService.search(pageable, q, minPrice, maxPrice);

		pedidosPage = new PageWrapper<>(pedidosPage, pageable);

		return pagedResourcesAssembler.toModel(pedidosPage, productModelAssembler);
	}

	@GetMapping("/{id}")
	public ProductModel findById(@PathVariable Long id) {

		Product product = productService.findById(id);
		return productModelAssembler.toModel(product);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductModel add(@RequestBody @Valid ProductInput productInput) {

		Product product = productInputDisassembler.toDomainObject(productInput);
		product = productService.save(product);
		ProductModel productModel = productModelAssembler.toModel(product);

		ResourceUriHelper.addUriInResponseHeader(productModel.getId());

		return productModel;
	}

	@PutMapping("/{id}")
	public ProductModel update(@PathVariable Long id, @RequestBody @Valid ProductInput productInput) {

		try {
			Product productAtual = productService.findById(id);
			productInputDisassembler.copyToDomainObject(productInput, productAtual);
			productAtual = productService.save(productAtual);

			return productModelAssembler.toModel(productAtual);
		} catch (ProductNaoEncontradoException e) {
			throw new ProductNaoEncontradoException(id);
		}
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void remove(@PathVariable Long id) {

		productService.remove(id);
	}

}
