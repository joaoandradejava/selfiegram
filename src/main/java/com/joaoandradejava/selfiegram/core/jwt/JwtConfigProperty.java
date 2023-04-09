package com.joaoandradejava.selfiegram.core.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt")
public class JwtConfigProperty {

	/**
	 * Senha para criptografar o token jwt.
	 */
	private String senhaTokenJwt;

	/**
	 * Tempo de expiração do token jwt(milissegundos).
	 */
	private Long tempoExpiracaoTokenJwt;

	public JwtConfigProperty() {
	}

	public String getSenhaTokenJwt() {
		return senhaTokenJwt;
	}

	public void setSenhaTokenJwt(String senhaTokenJwt) {
		this.senhaTokenJwt = senhaTokenJwt;
	}

	public Long getTempoExpiracaoTokenJwt() {
		return tempoExpiracaoTokenJwt;
	}

	public void setTempoExpiracaoTokenJwt(Long tempoExpiracaoTokenJwt) {
		this.tempoExpiracaoTokenJwt = tempoExpiracaoTokenJwt;
	}

}
