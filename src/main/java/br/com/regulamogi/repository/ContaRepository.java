package br.com.regulamogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.regulamogi.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	
	
}
