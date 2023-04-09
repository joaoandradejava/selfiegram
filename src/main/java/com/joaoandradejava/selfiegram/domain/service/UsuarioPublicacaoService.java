package com.joaoandradejava.selfiegram.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandradejava.selfiegram.domain.exception.NegocioException;
import com.joaoandradejava.selfiegram.domain.exception.ObjetoNaoEncontradoException;
import com.joaoandradejava.selfiegram.domain.model.CurtidaPublicacao;
import com.joaoandradejava.selfiegram.domain.model.Publicacao;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.repository.CurtidaPublicacaoRepository;
import com.joaoandradejava.selfiegram.domain.repository.PublicacaoRepository;

@Service
public class UsuarioPublicacaoService {

	@Autowired
	private PublicacaoRepository publicacaoRepository;

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Autowired
	private CurtidaPublicacaoRepository curtidaPublicacaoRepository;

	private Publicacao buscarPorId(Long id) {
		return this.publicacaoRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("A publicação de id %d não foi encontrada no sistema!", id)));
	}

	public Publicacao buscarPublicacaoDoUsuario(Long usuarioId, Long publicacaoId) {
		this.crudUsuarioService.buscarPorId(usuarioId);
		this.buscarPorId(publicacaoId);

		return this.publicacaoRepository.buscarPublicacaoDoUsuario(usuarioId, publicacaoId)
				.orElseThrow(() -> new NegocioException(String
						.format("A publicação de id %d não pertence ao usuario de id %d", publicacaoId, usuarioId)));
	}

	@Transactional
	public Publicacao fazerPublicacao(Publicacao publicacao, Long usuarioId) {
		Usuario usuario = this.crudUsuarioService.buscarPorId(usuarioId);
		publicacao.setAutor(usuario);

		return this.publicacaoRepository.save(publicacao);
	}

	@Transactional
	public Publicacao atualizarPublicacao(Publicacao publicacao) {
		return this.publicacaoRepository.save(publicacao);
	}

	@Transactional
	public void deletarPublicacaoDoUsuario(Long usuarioId, Long publicacaoId) {
		this.buscarPublicacaoDoUsuario(usuarioId, publicacaoId);

		this.publicacaoRepository.deleteById(publicacaoId);
	}

	@Transactional
	public void curtirPublicacao(Long usuarioId, Long publicacaoId) {
		Usuario usuario = this.crudUsuarioService.buscarPorId(usuarioId);
		Publicacao publicacao = this.buscarPorId(publicacaoId);

		Boolean isJaCurtiu = this.curtidaPublicacaoRepository.verificarSeJaCurtiu(usuarioId, publicacaoId);

		if (isJaCurtiu) {
			return;
		}

		CurtidaPublicacao curtidaPublicacao = new CurtidaPublicacao();
		curtidaPublicacao.setPublicacao(publicacao);
		curtidaPublicacao.setUsuario(usuario);

		this.curtidaPublicacaoRepository.save(curtidaPublicacao);
	}

	@Transactional
	public void descurtirPublicacao(Long usuarioId, Long publicacaoId) {
		this.crudUsuarioService.buscarPorId(usuarioId);
		this.buscarPorId(publicacaoId);

		Boolean isJaCurtiu = this.curtidaPublicacaoRepository.verificarSeJaCurtiu(usuarioId, publicacaoId);

		if (!isJaCurtiu) {
			return;
		}
		this.curtidaPublicacaoRepository.deletarCurtidaDoUsuarioNaPublicacao(usuarioId, publicacaoId);
	}

}
