package com.joaoandradejava.selfiegram.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.joaoandradejava.selfiegram.domain.model.enumeration.Perfil;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "unique_nome_usuario", columnNames = { "nome_usuario" }),
		@UniqueConstraint(name = "unique_email", columnNames = { "email" }) })
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", initialValue = 1, allocationSize = 1)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;

	@Column(nullable = false)
	private String nomeCompleto;

	@Column(name = "nome_usuario", nullable = false)
	private String nomeUsuario;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;
	private String bio;
	private String avatarUrl;
	private Long qtdPublicacao = 0l;

	@CreationTimestamp
	private LocalDateTime dataCadastro;

	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;

	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "perfil", joinColumns = @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_usuario")))
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Perfil> perfis = new HashSet<>();

	@OneToMany(mappedBy = "autor", orphanRemoval = true)
	private List<Publicacao> publicacoes = new ArrayList<>();

	@OneToMany(mappedBy = "usuario", orphanRemoval = true)
	private List<CurtidaPublicacao> curtidasNasPublicacoes = new ArrayList<>();

	@OneToMany(mappedBy = "seguindo", orphanRemoval = true)
	private List<Relacionamento> seguidores = new ArrayList<>();

	@OneToMany(mappedBy = "seguidor", orphanRemoval = true)
	private List<Relacionamento> seguindos = new ArrayList<>();

	public Usuario() {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}

	public List<CurtidaPublicacao> getCurtidasNasPublicacoes() {
		return curtidasNasPublicacoes;
	}

	public void setCurtidasNasPublicacoes(List<CurtidaPublicacao> curtidasNasPublicacoes) {
		this.curtidasNasPublicacoes = curtidasNasPublicacoes;
	}

	public List<Relacionamento> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<Relacionamento> seguidores) {
		this.seguidores = seguidores;
	}

	public List<Relacionamento> getSeguindos() {
		return seguindos;
	}

	public void setSeguindos(List<Relacionamento> seguindos) {
		this.seguindos = seguindos;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

}
