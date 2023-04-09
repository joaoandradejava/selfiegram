package com.joaoandradejava.selfiegram.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandradejava.selfiegram.api.input.ComentarioInput;
import com.joaoandradejava.selfiegram.api.model.ComentarioPublicacaoModel;
import com.joaoandradejava.selfiegram.domain.model.ComentarioPublicacao;
import com.joaoandradejava.selfiegram.domain.service.ComentarioPublicacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios/{usuarioId}/publicacoes/{publicacaoId}/comentarios")
public class UsuarioPublicacaoComentarioController {

	@Autowired
	private ComentarioPublicacaoService comentarioPublicacaoService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ComentarioPublicacaoModel fazerComentario(@Valid @RequestBody ComentarioInput comentarioInput,
			@PathVariable Long usuarioId, @PathVariable Long publicacaoId) {
		ComentarioPublicacao comentarioPublicacao = this.comentarioPublicacaoService
				.fazerComentario(comentarioInput.getComentario(), usuarioId, publicacaoId);

		return new ComentarioPublicacaoModel(comentarioPublicacao);
	}

	@PutMapping("/{comentarioPublicacaoId}")
	public ComentarioPublicacaoModel atualizarComentario(@PathVariable Long usuarioId, @PathVariable Long publicacaoId,
			@PathVariable Long comentarioPublicacaoId, @Valid @RequestBody ComentarioInput comentarioInput) {
		ComentarioPublicacao comentarioPublicacao = this.comentarioPublicacaoService
				.buscarComentarioDaPublicacaoDoUsuario(usuarioId, publicacaoId, comentarioPublicacaoId);
		comentarioPublicacao.setComentario(comentarioInput.getComentario());
		comentarioPublicacao = this.comentarioPublicacaoService.atualizarComentario(comentarioPublicacao);

		return new ComentarioPublicacaoModel(comentarioPublicacao);

	}

	@DeleteMapping("/{comentarioPublicacaoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarComentarioPorId(@PathVariable Long usuarioId, @PathVariable Long publicacaoId,
			@PathVariable Long comentarioPublicacaoId) {
		this.comentarioPublicacaoService.deletarComentarioPorId(usuarioId, publicacaoId, comentarioPublicacaoId);
	}
}
