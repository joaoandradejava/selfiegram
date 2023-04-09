package com.joaoandradejava.selfiegram.api.model;

import com.joaoandradejava.selfiegram.domain.model.Usuario;

public class UsuarioComNomeUsuarioEFotoPerfilModel {
	private Long id;
	private String nomeUsuario;
	private String avatarUrl;

	public UsuarioComNomeUsuarioEFotoPerfilModel() {
	}

	public UsuarioComNomeUsuarioEFotoPerfilModel(Usuario usuario) {
		this.id = usuario.getId();
		this.nomeUsuario = usuario.getNomeUsuario();
		this.avatarUrl = usuario.getAvatarUrl();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

}
