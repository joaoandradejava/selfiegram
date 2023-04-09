package com.joaoandradejava.selfiegram.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandradejava.selfiegram.domain.model.ComentarioPublicacao;
import com.joaoandradejava.selfiegram.domain.model.CurtidaComentario;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.repository.CurtidaComentarioRepository;

@Service
public class CurtidaComentarioPublicacaoService {

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Autowired
	private ComentarioPublicacaoService comentarioPublicacaoService;

	@Autowired
	private CurtidaComentarioRepository curtidaComentarioRepository;

	@Transactional
	public void curtirComentario(Long comentarioId, Long usuarioId) {
		Usuario usuario = this.crudUsuarioService.buscarPorId(usuarioId);
		ComentarioPublicacao comentarioPublicacao = this.comentarioPublicacaoService
				.buscarComentarioPublicacaoPorId(comentarioId);

		Boolean isJaCurtiu = this.curtidaComentarioRepository.verificarSeJaCurtiu(usuarioId, comentarioId);

		if (isJaCurtiu) {
			return;
		}

		CurtidaComentario curtidaComentario = new CurtidaComentario();
		curtidaComentario.setComentario(comentarioPublicacao);
		curtidaComentario.setUsuario(usuario);
		this.curtidaComentarioRepository.save(curtidaComentario);
	}

	@Transactional
	public void descurtirComentario(Long comentarioId, Long usuarioId) {
		this.crudUsuarioService.buscarPorId(usuarioId);
		this.comentarioPublicacaoService.buscarComentarioPublicacaoPorId(comentarioId);

		Boolean isJaCurtiu = this.curtidaComentarioRepository.verificarSeJaCurtiu(usuarioId, comentarioId);

		if (!isJaCurtiu) {
			return;
		}

		this.curtidaComentarioRepository.descurtirComentario(comentarioId, usuarioId);
	}

}
