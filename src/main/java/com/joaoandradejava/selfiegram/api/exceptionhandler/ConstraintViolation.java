package com.joaoandradejava.selfiegram.api.exceptionhandler;

import com.joaoandradejava.selfiegram.domain.exception.ErroInternoServidorException;

public enum ConstraintViolation {
	UNIQUE_NOME_USUARIO("unique_nome_usuario", "Já existe esté nome de usuario."),
	UNIQUE_EMAIL("unique_email", "Já existe um usuario com esté email");

	private String nomeConstraint;
	private String mensagemConstraint;

	private ConstraintViolation(String nomeConstraint, String mensagemConstraint) {
		this.nomeConstraint = nomeConstraint;
		this.mensagemConstraint = mensagemConstraint;
	}

	public String getNomeConstraint() {
		return nomeConstraint;
	}

	public String getMensagemConstraint() {
		return mensagemConstraint;
	}

	public static ConstraintViolation getConstraint(String nomeConstraint) {
		for (ConstraintViolation constraintViolation : ConstraintViolation.values()) {
			if (nomeConstraint.equalsIgnoreCase(constraintViolation.getNomeConstraint())) {
				return constraintViolation;
			}
		}

		throw new ErroInternoServidorException(String.format("Não existe a constraint '%s'", nomeConstraint));
	}

}
