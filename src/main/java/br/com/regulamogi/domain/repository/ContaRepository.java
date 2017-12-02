package br.com.regulamogi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.regulamogi.domain.Conta;
import br.com.regulamogi.domain.Perfil;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	List<Conta> findByPerfilNotLike(Perfil perfil);
	
}
