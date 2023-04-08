package com.joaoandradejava.selfiegram.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandradejava.selfiegram.api.input.PublicacaoInput;
import com.joaoandradejava.selfiegram.domain.model.Publicacao;

@Component
public class PublicacaoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Publicacao toModel(PublicacaoInput publicacaoInput) {
		return this.modelMapper.map(publicacaoInput, Publicacao.class);
	}

	public void copyToDomainModel(PublicacaoInput publicacaoInput, Publicacao publicacao) {
		this.modelMapper.map(publicacaoInput, publicacao);
	}
}
