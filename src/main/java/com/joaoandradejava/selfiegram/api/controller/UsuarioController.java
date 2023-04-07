package com.joaoandradejava.selfiegram.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandradejava.selfiegram.api.assembler.UsuarioFullModelAssembler;
import com.joaoandradejava.selfiegram.api.disassembler.UsuarioCreateInputDisassembler;
import com.joaoandradejava.selfiegram.api.input.UsuarioCreateInput;
import com.joaoandradejava.selfiegram.api.model.UsuarioFullModel;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.service.CrudUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Autowired
	private UsuarioFullModelAssembler usuarioFullModelAssembler;

	@Autowired
	private UsuarioCreateInputDisassembler usuarioCreateInputDisassembler;

	@GetMapping("/{id}")
	public UsuarioFullModel buscarPorId(@PathVariable Long id) {
		Usuario usuario = this.crudUsuarioService.buscarPorId(id);

		return this.usuarioFullModelAssembler.toModel(usuario);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public UsuarioFullModel realizarCadastro(@Valid @RequestBody UsuarioCreateInput usuarioCreateInput) {
		Usuario usuario = this.crudUsuarioService
				.realizarCadastro(this.usuarioCreateInputDisassembler.toDomainModel(usuarioCreateInput));

		return this.usuarioFullModelAssembler.toModel(usuario);
	}
}
