package com.joaoandradejava.selfiegram.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandradejava.selfiegram.domain.model.ComentarioPublicacao;

@Repository
public interface ComentarioPublicacaoRepository extends JpaRepository<ComentarioPublicacao, Long> {

	@Query("select c from ComentarioPublicacao c where c.usuario.id = :usuarioId and c.id = :comentarioPublicacaoId")
	public Optional<ComentarioPublicacao> buscarComentarioDaPublicacaoDoUsuario(Long usuarioId,
			Long comentarioPublicacaoId);

	@Query("select c from ComentarioPublicacao c where c.publicacao.id = :publicacaoId")
	public Page<ComentarioPublicacao> buscarComentariosDaPublicacao(Pageable pageable, Long publicacaoId);

}
