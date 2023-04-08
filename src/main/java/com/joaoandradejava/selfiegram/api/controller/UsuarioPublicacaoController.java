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

import com.joaoandradejava.selfiegram.api.disassembler.PublicacaoInputDisassembler;
import com.joaoandradejava.selfiegram.api.input.PublicacaoInput;
import com.joaoandradejava.selfiegram.api.model.PublicacaoFullModel;
import com.joaoandradejava.selfiegram.domain.model.Publicacao;
import com.joaoandradejava.selfiegram.domain.service.UsuarioPublicacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios/{usuarioId}/publicacoes")
public class UsuarioPublicacaoController {

	@Autowired
	private PublicacaoInputDisassembler publicacaoInputDisassembler;

	@Autowired
	private UsuarioPublicacaoService usuarioPublicacaoService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public PublicacaoFullModel fazerPublicacao(@Valid @RequestBody PublicacaoInput publicacaoInput,
			@PathVariable Long usuarioId) {
		Publicacao publicacao = this.usuarioPublicacaoService
				.fazerPublicacao(this.publicacaoInputDisassembler.toModel(publicacaoInput), usuarioId);

		return new PublicacaoFullModel(publicacao);
	}

	@PutMapping("/{publicacaoId}")
	public PublicacaoFullModel atualizarPublicacao(@Valid @RequestBody PublicacaoInput publicacaoInput,
			@PathVariable Long usuarioId, @PathVariable Long publicacaoId) {
		Publicacao publicacao = this.usuarioPublicacaoService.buscarPublicacaoDoUsuario(usuarioId, publicacaoId);
		this.publicacaoInputDisassembler.copyToDomainModel(publicacaoInput, publicacao);
		publicacao = this.usuarioPublicacaoService.atualizarPublicacao(publicacao);

		return new PublicacaoFullModel(publicacao);
	}

	@DeleteMapping("/{publicacaoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPublicacaoDoUsuario(@PathVariable Long usuarioId, @PathVariable Long publicacaoId) {
		this.usuarioPublicacaoService.deletarPublicacaoDoUsuario(usuarioId, publicacaoId);
	}

	@PutMapping("/{publicacaoId}/curti")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void curtirPublicacao(@PathVariable Long usuarioId, @PathVariable Long publicacaoId) {
		this.usuarioPublicacaoService.curtirPublicacao(usuarioId, publicacaoId);
	}

	@DeleteMapping("/{publicacaoId}/curti")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void descurtirPublicacao(@PathVariable Long usuarioId, @PathVariable Long publicacaoId) {
		this.usuarioPublicacaoService.descurtirPublicacao(usuarioId, publicacaoId);
	}
}
