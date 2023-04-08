package com.joaoandradejava.selfiegram.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandradejava.selfiegram.domain.service.UsuarioRelacionamentoService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/relacionamentos/{usuarioRelacionamentoId}")
public class UsuarioRelacionamento {

	@Autowired
	private UsuarioRelacionamentoService usuarioRelacionamentoService;

	@PutMapping("/segui")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void seguir(@PathVariable Long usuarioId, @PathVariable Long usuarioRelacionamentoId) {
		this.usuarioRelacionamentoService.seguir(usuarioId, usuarioRelacionamentoId);
	}

	@DeleteMapping("/segui")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deixarDeSeguir(@PathVariable Long usuarioId, @PathVariable Long usuarioRelacionamentoId) {
		this.usuarioRelacionamentoService.deixarDeSeguir(usuarioId, usuarioRelacionamentoId);
	}

}
