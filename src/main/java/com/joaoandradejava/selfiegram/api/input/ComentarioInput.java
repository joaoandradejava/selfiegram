package com.joaoandradejava.selfiegram.api.input;

import jakarta.validation.constraints.NotBlank;

public class ComentarioInput {

	@NotBlank
	private String comentario;

	public ComentarioInput() {
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
