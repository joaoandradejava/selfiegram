package com.joaoandradejava.selfiegram.domain.model.enumeration;

public enum Perfil {
	USUARIO("ROLE_USUARIO");

	private String descricao;

	private Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
