package com.joaoandradejava.selfiegram.api.input;

public class PublicacaoInput {
	private String avatarUrl;
	private String descricao;

	public PublicacaoInput() {
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

}
