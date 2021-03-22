package com.compassouol.productms.domain.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.compassouol.productms.core.data.PageableTranslator;
import com.compassouol.productms.domain.exception.not_found.ProductNaoEncontradoException;
import com.compassouol.productms.domain.model.Product;
import com.compassouol.productms.domain.repository.ProductRepository;
import com.compassouol.productms.domain.repository.spec.ProductSpecs;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Page<Product> listAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	public Page<Product> search(Pageable pageable, String q, BigDecimal minPrice,
			BigDecimal maxPrice) {
		
		Pageable pageableTraduzido = traduzirPageable(pageable);
		
		return productRepository.findAll(ProductSpecs.search(q, minPrice, maxPrice),
				pageableTraduzido);
	}
	
	@Transactional
	public Product save(Product estado) {
		return productRepository.save(estado);
	}
	
	@Transactional
	public void remove(Long id) {
		try {
			productRepository.deleteById(id);
			productRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ProductNaoEncontradoException(id);
		}
	}

	public Product findById(Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new ProductNaoEncontradoException(id));
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of("name", "name", "description", "description", "price", "price");

		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
