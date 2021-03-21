package com.compassouol.productms.domain.exception.not_found;

public class ProductNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProductNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ProductNaoEncontradoException(Long idProduct) {
		this(String.format("Não existe um registro de um produto com o id %d", idProduct));
	}
	
}
