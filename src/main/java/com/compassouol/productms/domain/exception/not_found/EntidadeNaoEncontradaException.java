package com.compassouol.productms.domain.exception.not_found;

import com.compassouol.productms.domain.exception.DomainException;

public abstract class EntidadeNaoEncontradaException extends DomainException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
}
