package com.joaoandradejava.selfiegram.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandradejava.selfiegram.api.model.ComentarioPublicacaoModel;
import com.joaoandradejava.selfiegram.api.model.CurtidaPublicacaoModel;
import com.joaoandradejava.selfiegram.domain.model.ComentarioPublicacao;
import com.joaoandradejava.selfiegram.domain.model.CurtidaPublicacao;
import com.joaoandradejava.selfiegram.domain.service.PublicacaoComentarioService;
import com.joaoandradejava.selfiegram.domain.service.PublicacaoCurtidaService;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

	@Autowired
	private PublicacaoCurtidaService publicacaoCurtidaService;

	@Autowired
	private PublicacaoComentarioService publicacaoComentarioService;

	@GetMapping("/{publicacaoId}/curtidas")
	public Page<CurtidaPublicacaoModel> buscarCurtidasDaPublicacao(Pageable pageable, @PathVariable Long publicacaoId) {
		Page<CurtidaPublicacao> page = this.publicacaoCurtidaService.buscarCurtidasDaPublicacao(publicacaoId, pageable);

		return page.map(x -> new CurtidaPublicacaoModel(x));

	}

	@GetMapping("/{publicacaoId}/comentarios")
	public Page<ComentarioPublicacaoModel> buscarComentariosDaPublicacao(Pageable pageable,
			@PathVariable Long publicacaoId) {
		Page<ComentarioPublicacao> page = this.publicacaoComentarioService
				.buscarComentariosDaPublicacao(pageable, publicacaoId);

		return page.map(x -> new ComentarioPublicacaoModel(x));
	}

}
