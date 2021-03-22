package com.compassouol.productms.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInput {

	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotNull
	@Positive
	private BigDecimal price;
}
