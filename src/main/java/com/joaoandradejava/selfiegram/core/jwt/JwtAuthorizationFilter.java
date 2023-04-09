package com.joaoandradejava.selfiegram.core.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.joaoandradejava.selfiegram.core.security.UsuarioLogado;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private JwtUtil jwtUtil;
	private UsuarioRepository usuarioRepository;

	public JwtAuthorizationFilter(JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
		super();
		this.jwtUtil = jwtUtil;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader("Authorization");

		if (StringUtils.hasLength(token) && token.startsWith("Bearer ")) {
			token = token.replaceAll("Bearer ", "");
			if (this.jwtUtil.isTokenJwtValido(token)) {
				UsernamePasswordAuthenticationToken authentication = this.getAuthentication(token);
				if (authentication != null) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
					;
				}
			}

		}

		filterChain.doFilter(request, response);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		Long id = this.jwtUtil.getSubject(token);
		if (id != null) {
			Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
			if (usuario != null) {
				UsuarioLogado usuarioLogado = new UsuarioLogado(usuario);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						usuarioLogado, null, usuarioLogado.getAuthorities());

				return authentication;
			}
		}

		return null;
	}

}
