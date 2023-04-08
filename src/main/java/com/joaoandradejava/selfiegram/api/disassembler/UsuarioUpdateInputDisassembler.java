package com.joaoandradejava.selfiegram.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandradejava.selfiegram.api.input.UsuarioUpdateInput;
import com.joaoandradejava.selfiegram.domain.model.Usuario;

@Component
public class UsuarioUpdateInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public void copyToDomainModel(UsuarioUpdateInput usuarioUpdateInput, Usuario usuario) {
		this.modelMapper.map(usuarioUpdateInput, usuario);
	}
}
