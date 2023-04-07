package com.joaoandradejava.selfiegram.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandradejava.selfiegram.api.model.UsuarioFullModel;
import com.joaoandradejava.selfiegram.domain.model.Usuario;

@Component
public class UsuarioFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioFullModel toModel(Usuario usuario) {
		return this.modelMapper.map(usuario, UsuarioFullModel.class);
	}
}
