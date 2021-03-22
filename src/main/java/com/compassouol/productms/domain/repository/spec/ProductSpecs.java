package com.compassouol.productms.domain.repository.spec;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.compassouol.productms.domain.model.Product;

public class ProductSpecs {

	public static Specification<Product> search(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		return (root, query, builder) -> {

			var predicates = new ArrayList<Predicate>();

			if (StringUtils.hasText(q)) {
				
				var predicateName = builder.like(root.get("name"), "%" + q + "%");
				var predicateDescription = builder.like(root.get("description"), "%" + q + "%");
				
				predicates.add(builder.or(predicateName, predicateDescription));
			}

			if (minPrice != null) {
				
				predicates.add(builder.greaterThanOrEqualTo(root.get("price"), minPrice));
			}

			if (maxPrice != null) {
				
				predicates.add(builder.lessThanOrEqualTo(root.get("price"), maxPrice));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
