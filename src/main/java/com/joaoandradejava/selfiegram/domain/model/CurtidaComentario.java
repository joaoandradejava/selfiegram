package com.joaoandradejava.selfiegram.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_curtida_comentario", sequenceName = "seq_curtida_comentario", initialValue = 1, allocationSize = 1)
public class CurtidaComentario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_curtida_comentario")
	private Long id;

	@CreationTimestamp
	private LocalDateTime dataCurtida;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_comentario"))
	private ComentarioPublicacao comentario;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_usuario"))
	private Usuario usuario;

	public CurtidaComentario() {
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

	public ComentarioPublicacao getComentario() {
		return comentario;
	}

	public void setComentario(ComentarioPublicacao comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		CurtidaComentario other = (CurtidaComentario) obj;
		return Objects.equals(id, other.id);
	}

}
