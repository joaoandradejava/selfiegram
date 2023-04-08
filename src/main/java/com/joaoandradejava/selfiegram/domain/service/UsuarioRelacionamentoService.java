package com.joaoandradejava.selfiegram.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandradejava.selfiegram.domain.model.Relacionamento;
import com.joaoandradejava.selfiegram.domain.model.Usuario;
import com.joaoandradejava.selfiegram.domain.repository.RelacionamentoRepository;

@Service
public class UsuarioRelacionamentoService {

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Autowired
	private RelacionamentoRepository relacionamentoRepository;

	@Transactional
	public void seguir(Long usuarioId, Long usuarioRelacionamentoId) {
		if (usuarioId == usuarioRelacionamentoId) {
			return;
		}

		Boolean isJaSeguiu = this.relacionamentoRepository.verificarSeJaCurtiu(usuarioId, usuarioRelacionamentoId);

		if (isJaSeguiu) {
			return;
		}

		Usuario usuario = this.crudUsuarioService.buscarPorId(usuarioId);
		Usuario usuarioRelacionamento = this.crudUsuarioService.buscarPorId(usuarioRelacionamentoId);

		Relacionamento relacionamento = new Relacionamento();
		relacionamento.setSeguidor(usuario);
		relacionamento.setSeguindo(usuarioRelacionamento);

		this.relacionamentoRepository.save(relacionamento);
	}

	@Transactional
	public void deixarDeSeguir(Long usuarioId, Long usuarioRelacionamentoId) {
		if (usuarioId == usuarioRelacionamentoId) {
			return;
		}

		Boolean isJaSeguiu = this.relacionamentoRepository.verificarSeJaCurtiu(usuarioId, usuarioRelacionamentoId);

		if (!isJaSeguiu) {
			return;
		}

		this.relacionamentoRepository.deixarDeSeguir(usuarioId, usuarioRelacionamentoId);
	}

}
