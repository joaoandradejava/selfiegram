package com.joaoandradejava.selfiegram.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaoandradejava.selfiegram.domain.model.Relacionamento;

@Repository
public interface RelacionamentoRepository extends JpaRepository<Relacionamento, Long> {

}
