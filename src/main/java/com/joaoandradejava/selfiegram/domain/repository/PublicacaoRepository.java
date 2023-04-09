package com.joaoandradejava.selfiegram.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandradejava.selfiegram.domain.model.Publicacao;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

	@Query("select p from Publicacao p where p.id = :publicacaoId and p.autor.id = :usuarioId")
	public Optional<Publicacao> buscarPublicacaoDoUsuario(Long usuarioId, Long publicacaoId);

}
