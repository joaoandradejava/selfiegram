package com.joaoandradejava.selfiegram.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.joaoandradejava.selfiegram.domain.model.CurtidaPublicacao;
import com.joaoandradejava.selfiegram.domain.repository.CurtidaPublicacaoRepository;

@Service
public class PublicacaoCurtidaService {

	@Autowired
	private CurtidaPublicacaoRepository curtidaPublicacaoRepository;

	public Page<CurtidaPublicacao> buscarCurtidasDaPublicacao(Long publicacaoId, Pageable pageable) {
		return this.curtidaPublicacaoRepository.buscarCurtidasDaPublicacao(pageable, publicacaoId);
	}

}
