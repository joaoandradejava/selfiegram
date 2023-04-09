package com.joaoandradejava.selfiegram.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandradejava.selfiegram.domain.exception.NegocioException;
import com.joaoandradejava.selfiegram.domain.exception.ObjetoNaoEncontradoException;
import com.joaoandradejava.selfiegram.domain.model.ComentarioPublicacao;
import com.joaoandradejava.selfiegram.domain.model.Publicacao;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.repository.ComentarioPublicacaoRepository;

@Service
public class ComentarioPublicacaoService {

	@Autowired
	private ComentarioPublicacaoRepository comentarioPublicacaoRepository;

	@Autowired
	private UsuarioPublicacaoService usuarioPublicacaoService;

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	public ComentarioPublicacao buscarComentarioPublicacaoPorId(Long comentarioPublicacaoId) {
		return this.comentarioPublicacaoRepository.findById(comentarioPublicacaoId)
				.orElseThrow(() -> new ObjetoNaoEncontradoException(String.format(
						"Não foi encontrado o comentario da publicação de id %d no sistema!", comentarioPublicacaoId)));
	}

	public ComentarioPublicacao buscarComentarioDaPublicacaoDoUsuario(Long usuarioId, Long publicacaoId,
			Long comentarioPublicacaoId) {
		this.crudUsuarioService.buscarPorId(usuarioId);
		this.usuarioPublicacaoService.buscarPublicacaoPorId(publicacaoId);
		this.buscarComentarioPublicacaoPorId(comentarioPublicacaoId);

		return this.comentarioPublicacaoRepository
				.buscarComentarioDaPublicacaoDoUsuario(usuarioId, comentarioPublicacaoId)
				.orElseThrow(() -> new NegocioException(
						String.format("O Comentario da publicação de id %d não pertence ao usuario de id %d",
								comentarioPublicacaoId, usuarioId)));
	}

	@Transactional
	public ComentarioPublicacao fazerComentario(String comentario, Long usuarioId, Long publicacaoId) {
		Usuario usuario = this.crudUsuarioService.buscarPorId(usuarioId);
		Publicacao publicacao = this.usuarioPublicacaoService.buscarPublicacaoPorId(publicacaoId);

		ComentarioPublicacao comentarioPublicacao = new ComentarioPublicacao();
		comentarioPublicacao.setComentario(comentario);
		comentarioPublicacao.setUsuario(usuario);
		comentarioPublicacao.setPublicacao(publicacao);
		return this.comentarioPublicacaoRepository.save(comentarioPublicacao);
	}

	@Transactional
	public ComentarioPublicacao atualizarComentario(ComentarioPublicacao comentarioPublicacao) {
		return this.comentarioPublicacaoRepository.save(comentarioPublicacao);
	}

	@Transactional
	public void deletarComentarioPorId(Long usuarioId, Long publicacaoId, Long comentarioPublicacaoId) {
		this.buscarComentarioDaPublicacaoDoUsuario(usuarioId, publicacaoId, comentarioPublicacaoId);

		this.comentarioPublicacaoRepository.deleteById(comentarioPublicacaoId);
	}

}
