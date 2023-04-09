package com.joaoandradejava.selfiegram.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandradejava.selfiegram.api.model.CurtidaPublicacaoModel;
import com.joaoandradejava.selfiegram.domain.model.CurtidaPublicacao;
import com.joaoandradejava.selfiegram.domain.service.PublicacaoCurtidaService;

@RestController
@RequestMapping("/publicacoes/{publicacaoId}/curtidas")
public class PublicacaoCurtidaController {

	@Autowired
	private PublicacaoCurtidaService publicacaoCurtidaService;

	@GetMapping
	public Page<CurtidaPublicacaoModel> buscarCurtidasDaPublicacao(Pageable pageable, @PathVariable Long publicacaoId) {
		Page<CurtidaPublicacao> page = this.publicacaoCurtidaService.buscarCurtidasDaPublicacao(publicacaoId, pageable);

		return page.map(x -> new CurtidaPublicacaoModel(x));

	}

}
