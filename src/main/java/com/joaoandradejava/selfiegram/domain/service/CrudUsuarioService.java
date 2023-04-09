package com.joaoandradejava.selfiegram.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joaoandradejava.selfiegram.domain.exception.ObjetoNaoEncontradoException;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CrudUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Usuario buscarPorId(Long id) {
		return this.repository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Usuario de id %d não foi encontrado no sistema!", id)));
	}

	@Transactional
	public Usuario realizarCadastro(Usuario usuario) {
		usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));

		return this.repository.save(usuario);
	}

	@Transactional
	public Usuario atualizarPerfil(Usuario usuario) {
		return this.repository.save(usuario);
	}

	@Transactional
	public void deletarPorId(Long id) {
		this.buscarPorId(id);

		this.repository.deleteById(id);
	}

}
