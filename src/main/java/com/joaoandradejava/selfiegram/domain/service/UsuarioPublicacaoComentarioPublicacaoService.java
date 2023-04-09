package com.joaoandradejava.selfiegram.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandradejava.selfiegram.domain.exception.NegocioException;
import com.joaoandradejava.selfiegram.domain.exception.ObjetoNaoEncontradoException;
import com.joaoandradejava.selfiegram.domain.model.ComentarioPublicacao;
import com.joaoandradejava.selfiegram.domain.model.Publicacao;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.repository.ComentarioPublicacaoRepository;

@Service
public class UsuarioPublicacaoComentarioPublicacaoService {

	@Autowired
	private ComentarioPublicacaoRepository comentarioPublicacaoRepository;

	@Autowired
	private UsuarioPublicacaoService usuarioPublicacaoService;

	public Page<ComentarioPublicacao> buscarComentariosDaPublicacao(Pageable pageable, Long usuarioId,
			Long publicacaoId) {
		return this.comentarioPublicacaoRepository.buscarComentariosDaPublicacao(pageable, usuarioId, publicacaoId);
	}

	private ComentarioPublicacao buscarComentarioDaPublicacaoPorId(Long comentarioPublicacaoId) {
		return this.comentarioPublicacaoRepository.findById(comentarioPublicacaoId)
				.orElseThrow(() -> new ObjetoNaoEncontradoException(String.format(
						"O Comentario da publicação de id %d não foi encontrado no sistema!", comentarioPublicacaoId)));
	}

	public ComentarioPublicacao buscarComentarioDaPublicacaoDoUsuario(Long usuarioId, Long publicacaoId,
			Long comentarioPublicacaoId) {
		this.usuarioPublicacaoService.buscarPublicacaoDoUsuario(usuarioId, publicacaoId);
		this.buscarComentarioDaPublicacaoPorId(comentarioPublicacaoId);

		return this.comentarioPublicacaoRepository
				.buscarComentarioDaPublicacaoDoUsuario(usuarioId, publicacaoId, comentarioPublicacaoId)
				.orElseThrow(() -> new NegocioException(String.format(
						"O Comentario da publicação de id %d não pertence ao usuario de %d e publicação de id %d",
						comentarioPublicacaoId, usuarioId, publicacaoId)));
	}

	@Transactional
	public ComentarioPublicacao fazerComentario(String comentario, Long usuarioId, Long publicacaoId) {
		Publicacao publicacao = this.usuarioPublicacaoService.buscarPublicacaoDoUsuario(usuarioId, publicacaoId);
		Usuario usuario = publicacao.getAutor();

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
