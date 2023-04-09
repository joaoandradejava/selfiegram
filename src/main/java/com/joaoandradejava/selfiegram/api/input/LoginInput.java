package com.joaoandradejava.selfiegram.api.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginInput {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	public LoginInput() {
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

}