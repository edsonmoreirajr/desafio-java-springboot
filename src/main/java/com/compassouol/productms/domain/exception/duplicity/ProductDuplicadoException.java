package com.compassouol.productms.domain.exception.duplicity;

public class ProductDuplicadoException extends EntidadeDuplicadaException {

	private static final long serialVersionUID = 1L;

	public ProductDuplicadoException(String nome) {
		super(String.format("Já existe um produto cadastrado com o nome %s", nome));
	}
}
