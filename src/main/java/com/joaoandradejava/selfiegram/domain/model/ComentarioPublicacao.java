package com.joaoandradejava.selfiegram.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_comentario_publicacao", sequenceName = "seq_comentario_publicacao", initialValue = 1, allocationSize = 1)
public class ComentarioPublicacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comentario_publicacao")
	private Long id;

	@Column(nullable = false)
	private String nomeUsuario;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime dataComentario;

	@UpdateTimestamp
	private LocalDateTime dataAtualizacaoComentario;

	@Column(nullable = false)
	private String comentario;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_usuario"))
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_publicacao"))
	private Publicacao publicacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_resposta"))
	private ComentarioPublicacao resposta;

	public ComentarioPublicacao() {
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

	public LocalDateTime getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(LocalDateTime dataComentario) {
		this.dataComentario = dataComentario;
	}

	public LocalDateTime getDataAtualizacaoComentario() {
		return dataAtualizacaoComentario;
	}

	public void setDataAtualizacaoComentario(LocalDateTime dataAtualizacaoComentario) {
		this.dataAtualizacaoComentario = dataAtualizacaoComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}

	public ComentarioPublicacao getResposta() {
		return resposta;
	}

	public void setResposta(ComentarioPublicacao resposta) {
		this.resposta = resposta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComentarioPublicacao other = (ComentarioPublicacao) obj;
		return Objects.equals(id, other.id);
	}

}
