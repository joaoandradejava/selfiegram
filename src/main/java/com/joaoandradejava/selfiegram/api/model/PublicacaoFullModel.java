package com.joaoandradejava.selfiegram.api.model;

import java.time.LocalDateTime;

import com.joaoandradejava.selfiegram.domain.model.Publicacao;

public class PublicacaoFullModel {
	private Long id;
	private String avatarUrl;
	private String descricao;
	private LocalDateTime dataPublicacao;
	private LocalDateTime dataAtualizacao;
	private Long qtdCurtidas = 0l;
	private Long qtdComentarios = 0l;
	private String autor;
	private String avatarUrlAutor;

	public PublicacaoFullModel(Publicacao publicacao) {
		this.id = publicacao.getId();
		this.avatarUrl = publicacao.getAvatarUrl();
		this.descricao = publicacao.getDescricao();
		this.dataPublicacao = publicacao.getDataPublicacao();
		this.dataAtualizacao = publicacao.getDataAtualizacao();
		this.autor = publicacao.getAutor().getNomeUsuario();
		this.avatarUrlAutor = publicacao.getAutor().getAvatarUrl();
		this.setQtdCurtidas((long) publicacao.getCurtidas().size());
		this.setQtdComentarios((long) publicacao.getComentarios().size());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDateTime dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Long getQtdCurtidas() {
		return qtdCurtidas;
	}

	public void setQtdCurtidas(Long qtdCurtidas) {
		this.qtdCurtidas = qtdCurtidas;
	}

	public Long getQtdComentarios() {
		return qtdComentarios;
	}

	public void setQtdComentarios(Long qtdComentarios) {
		this.qtdComentarios = qtdComentarios;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAvatarUrlAutor() {
		return avatarUrlAutor;
	}

	public void setAvatarUrlAutor(String avatarUrlAutor) {
		this.avatarUrlAutor = avatarUrlAutor;
	}

}
