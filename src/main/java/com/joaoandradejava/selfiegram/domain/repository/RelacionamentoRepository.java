package com.joaoandradejava.selfiegram.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandradejava.selfiegram.domain.model.Relacionamento;

@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, Long> {

	@Query("select new java.lang.Boolean(count(*) > 0) from Relacionamento r where r.seguidor.id = :usuarioId and r.seguindo.id = :relacionamentoId")
	public Boolean verificarSeJaCurtiu(Long usuarioId, Long relacionamentoId);

	@Modifying
	@Query("delete from Relacionamento r where r.seguidor.id = :usuarioId and r.seguindo.id = :relacionamentoId")
	public void deixarDeSeguir(Long usuarioId, Long relacionamentoId);
}
