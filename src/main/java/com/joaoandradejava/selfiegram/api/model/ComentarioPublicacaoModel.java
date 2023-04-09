package com.joaoandradejava.selfiegram.api.model;

import java.time.LocalDateTime;

import com.joaoandradejava.selfiegram.domain.model.ComentarioPublicacao;

public class ComentarioPublicacaoModel {
	private Long id;
	private LocalDateTime dataComentario;
	private LocalDateTime dataAtualizacao;
	private String comentario;
	private UsuarioComNomeUsuarioEFotoPerfilModel usuario;

	public ComentarioPublicacaoModel(ComentarioPublicacao comentarioPublicacao) {
		this.id = comentarioPublicacao.getId();
		this.dataComentario = comentarioPublicacao.getDataComentario();
		this.dataAtualizacao = comentarioPublicacao.getDataAtualizacaoComentario();
		this.comentario = comentarioPublicacao.getComentario();
		this.usuario = new UsuarioComNomeUsuarioEFotoPerfilModel(comentarioPublicacao.getUsuario());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(LocalDateTime dataComentario) {
		this.dataComentario = dataComentario;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public UsuarioComNomeUsuarioEFotoPerfilModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioComNomeUsuarioEFotoPerfilModel usuario) {
		this.usuario = usuario;
	}

}
