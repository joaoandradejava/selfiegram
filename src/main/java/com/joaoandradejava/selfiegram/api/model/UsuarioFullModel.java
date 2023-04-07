package com.joaoandradejava.selfiegram.api.model;

import java.time.LocalDateTime;

public class UsuarioFullModel {
	private Long id;
	private String nomeCompleto;
	private String nomeUsuario;
	private String email;
	private String bio;
	private String avatarUrl;
	private Long qtdSeguidores;
	private Long qtdSeguindo;
	private Long qtdPublicacao;
	private LocalDateTime dataCadastro;
	private LocalDateTime dataAtualizacao;

	public UsuarioFullModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Long getQtdSeguidores() {
		return qtdSeguidores;
	}

	public void setQtdSeguidores(Long qtdSeguidores) {
		this.qtdSeguidores = qtdSeguidores;
	}

	public Long getQtdSeguindo() {
		return qtdSeguindo;
	}

	public void setQtdSeguindo(Long qtdSeguindo) {
		this.qtdSeguindo = qtdSeguindo;
	}

	public Long getQtdPublicacao() {
		return qtdPublicacao;
	}

	public void setQtdPublicacao(Long qtdPublicacao) {
		this.qtdPublicacao = qtdPublicacao;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

}
