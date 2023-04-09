package com.joaoandradejava.selfiegram.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandradejava.selfiegram.domain.model.CurtidaComentario;

@Repository
public interface CurtidaComentarioRepository extends JpaRepository<CurtidaComentario, Long> {

	@Query("select new java.lang.Boolean(count(*) > 0) from CurtidaComentario c where c.usuario.id = :usuarioId and c.comentario.id = :comentarioId")
	public Boolean verificarSeJaCurtiu(Long usuarioId, Long comentarioId);

	@Modifying
	@Query("delete from CurtidaComentario c where c.usuario.id = :usuarioId and c.comentario.id = :comentarioId")
	public void descurtirComentario(Long comentarioId, Long usuarioId);
}