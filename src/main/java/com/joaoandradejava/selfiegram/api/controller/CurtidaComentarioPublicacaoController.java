package com.joaoandradejava.selfiegram.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandradejava.selfiegram.domain.service.CurtidaComentarioPublicacaoService;

@RestController
@RequestMapping("/comentarios/{comentarioId}/usuarios/{usuarioId}/curtida")
public class CurtidaComentarioPublicacaoController {

	@Autowired
	private CurtidaComentarioPublicacaoService curtidaComentarioDaPublicacaoService;

	@PutMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void curtirComentario(@PathVariable Long comentarioId, @PathVariable Long usuarioId) {
		this.curtidaComentarioDaPublicacaoService.curtirComentario(comentarioId, usuarioId);
	}

	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void descurtirComentario(@PathVariable Long comentarioId, @PathVariable Long usuarioId) {
		this.curtidaComentarioDaPublicacaoService.descurtirComentario(comentarioId, usuarioId);
	}

}
