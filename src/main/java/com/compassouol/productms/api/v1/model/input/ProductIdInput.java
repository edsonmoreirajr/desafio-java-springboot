package com.compassouol.productms.api.v1.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductIdInput {

	@NotNull
	private Long id;
}
