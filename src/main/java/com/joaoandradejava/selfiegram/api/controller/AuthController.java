package com.joaoandradejava.selfiegram.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandradejava.selfiegram.api.input.LoginInput;
import com.joaoandradejava.selfiegram.api.model.UsuarioLogadoModel;
import com.joaoandradejava.selfiegram.core.jwt.JwtUtil;
import com.joaoandradejava.selfiegram.domain.exception.FalhaNaAutenticacaoException;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping
	public UsuarioLogadoModel realizarLogin(@Valid @RequestBody LoginInput loginInput) {
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					loginInput.getEmail(), loginInput.getSenha());
			this.authenticationManager.authenticate(authentication);
			Usuario usuario = this.repository.findByEmail(loginInput.getEmail()).get();
			String tokenJwt = "Bearer " + this.jwtUtil.gerarTokenJwt(usuario.getId());

			return new UsuarioLogadoModel(usuario, tokenJwt);
		} catch (BadCredentialsException e) {
			throw new FalhaNaAutenticacaoException(e.getMessage());

		}
	}
}
