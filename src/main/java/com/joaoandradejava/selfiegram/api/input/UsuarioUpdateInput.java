package com.joaoandradejava.selfiegram.api.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioUpdateInput {

	@NotBlank
	@Size(min = 3, max = 255)
	private String nomeCompleto;

	@NotBlank
	@Size(max = 255)
	private String nomeUsuario;

	@Email
	@NotBlank
	@Size(max = 255)
	private String email;

	@Size(max = 255)
	private String bio;

	public UsuarioUpdateInput() {

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

}
