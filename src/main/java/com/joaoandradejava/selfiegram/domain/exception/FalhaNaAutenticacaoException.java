package com.joaoandradejava.selfiegram.domain.exception;

public class FalhaNaAutenticacaoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public FalhaNaAutenticacaoException(String mensagem) {
		super(mensagem);
	}

}
