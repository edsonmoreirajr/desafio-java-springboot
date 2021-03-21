package com.compassouol.productms.domain.exception.duplicity;

import com.compassouol.productms.domain.exception.DomainException;

public abstract class EntidadeDuplicadaException extends DomainException {

	private static final long serialVersionUID = 1L;

	public EntidadeDuplicadaException(String mensagem) {
		super(mensagem);
	}
	
}
