package com.joaoandradejava.selfiegram.api.model;

import java.time.LocalDateTime;

import com.joaoandradejava.selfiegram.domain.model.CurtidaPublicacao;

public class CurtidaPublicacaoModel {
	private Long id;
	private LocalDateTime dataCurtida;
	private UsuarioComNomeUsuarioEFotoPerfilModel usuario;

	public CurtidaPublicacaoModel() {
	}

	public CurtidaPublicacaoModel(CurtidaPublicacao curtidaPublicacao) {
		this.id = curtidaPublicacao.getId();
		this.dataCurtida = curtidaPublicacao.getDataCurtida();
		this.usuario = new UsuarioComNomeUsuarioEFotoPerfilModel(curtidaPublicacao.getUsuario());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCurtida() {
		return dataCurtida;
	}

	public void setDataCurtida(LocalDateTime dataCurtida) {
		this.dataCurtida = dataCurtida;
	}

	public UsuarioComNomeUsuarioEFotoPerfilModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioComNomeUsuarioEFotoPerfilModel usuario) {
		this.usuario = usuario;
	}

}
