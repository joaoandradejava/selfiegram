package com.joaoandradejava.selfiegram.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.joaoandradejava.selfiegram.domain.model.ComentarioPublicacao;
import com.joaoandradejava.selfiegram.domain.repository.ComentarioPublicacaoRepository;

@Service
public class PublicacaoComentarioService {

	@Autowired
	private ComentarioPublicacaoRepository comentarioPublicacaoRepository;

	public Page<ComentarioPublicacao> buscarComentariosDaPublicacao(Pageable pageable, Long publicacaoId) {
		return this.comentarioPublicacaoRepository.buscarComentariosDaPublicacao(pageable, publicacaoId);
	}

}
