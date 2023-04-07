package com.joaoandradejava.selfiegram.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaoandradejava.selfiegram.domain.exception.ObjetoNaoEncontradoException;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CrudUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario buscarPorId(Long id) {
		return this.repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Usuario de id %d não foi encontrado no sistema!", id)));
	}

	@Transactional
	public Usuario realizarCadastro(Usuario usuario) {
		return this.repository.save(usuario);
	}

}
