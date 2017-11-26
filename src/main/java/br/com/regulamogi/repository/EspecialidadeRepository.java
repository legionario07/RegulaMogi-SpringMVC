package br.com.regulamogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.regulamogi.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

}
