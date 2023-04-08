package com.joaoandradejava.selfiegram.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandradejava.selfiegram.domain.model.CurtidaPublicacao;

@Repository
public interface CurtidaPublicacaoRepository extends JpaRepository<CurtidaPublicacao, Long> {

	@Query("select new java.lang.Boolean(count(*) > 0) from CurtidaPublicacao c where c.publicacao.id = :publicacaoId and c.usuario.id = :usuarioId")
	public Boolean verificarSeJaCurtiu(Long usuarioId, Long publicacaoId);

	@Modifying
	@Query("delete from CurtidaPublicacao c where c.publicacao.id = :publicacaoId and c.usuario.id = :usuarioId")
	public void deletarCurtidaDoUsuarioNaPublicacao(Long usuarioId, Long publicacaoId);
}
