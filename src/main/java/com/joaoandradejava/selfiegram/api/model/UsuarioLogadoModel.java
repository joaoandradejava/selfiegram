package com.joaoandradejava.selfiegram.api.model;

import com.joaoandradejava.selfiegram.domain.model.Usuario;

public class UsuarioLogadoModel {
	private Long id;
	private String nomeUsuario;
	private String avatarUrl;
	private String email;
	private String tokenJwt;

	public UsuarioLogadoModel(Usuario usuario, String tokenJwt) {
		this.id = usuario.getId();
		this.nomeUsuario = usuario.getNomeUsuario();
		this.avatarUrl = usuario.getAvatarUrl();
		this.email = usuario.getEmail();
		this.tokenJwt = tokenJwt;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTokenJwt() {
		return tokenJwt;
	}

	public void setTokenJwt(String tokenJwt) {
		this.tokenJwt = tokenJwt;
	}

}
