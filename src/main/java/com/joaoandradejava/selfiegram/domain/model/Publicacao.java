package com.joaoandradejava.selfiegram.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_publicacao", sequenceName = "seq_publicacao", initialValue = 1, allocationSize = 1)
public class Publicacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_publicacao")
	private Long id;
	private String avatarUrl;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime dataPublicacao;

	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;
	private Long qtdCurtidas = 0l;
	private Long qtdComentarios = 0l;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_autor"))
	private Usuario autor;

	@OneToMany(mappedBy = "publicacao", orphanRemoval = true)
	private List<CurtidaPublicacao> curtidas = new ArrayList<>();

	@OneToMany(mappedBy = "publicacao", orphanRemoval = true)
	private List<ComentarioPublicacao> comentarios = new ArrayList<>();

	public Publicacao() {
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

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public List<CurtidaPublicacao> getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(List<CurtidaPublicacao> curtidas) {
		this.curtidas = curtidas;
	}

	public List<ComentarioPublicacao> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioPublicacao> comentarios) {
		this.comentarios = comentarios;
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
		Publicacao other = (Publicacao) obj;
		return Objects.equals(id, other.id);
	}

}
